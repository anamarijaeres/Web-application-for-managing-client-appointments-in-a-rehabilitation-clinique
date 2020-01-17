import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class AddExercise extends Component {

 state= {
    name: '',
    image: null,
    description: '',
    burnedCaloriesEasy: '',
    burnedCaloriesNormal: '',
    burnedCaloriesHard: ''

 }

 handleImageChange = (e) => {
     this.setState(
         {
             image: e.target.files[0]
         }
     )
 }

 handleNameChange = (e) => {
      this.setState(
          {
              name: e.target.value
          }
      )
  }

 handleDescriptionChange = (e) => {
     this.setState(
         {
             description: e.target.value
         }
     )
 }

 handleBurnedCaloriesEasy = (e) => {
     this.setState(
         {
             burnedCaloriesEasy: e.target.value
         }
     )

 }

 handleBurnedCaloriesNormal = (e) => {
    this.setState(
        {
            burnedCaloriesNormal: e.target.value
        }
    )

}

handleBurnedCaloriesHard = (e) => {
    this.setState(
        {
            burnedCaloriesHard: e.target.value
        }
    )

}

handleSubmit = (e) => {
    e.preventDefault();

    const data={
        name: this.state.name,
        description: this.state.description,
        burnedCaloriesEasy: this.state.burnedCaloriesEasy,
        burnedCaloriesNormal: this.state.burnedCaloriesNormal,
        burnedCaloriesHard: this.state.burnedCaloriesHard
    };


    let formData=new FormData();
    formData.append('imageFile', this.state.image);

    return axios.post('http://localhost:8080/addExercise', data)
                .then(response=>{
                    if(response.data.error_code === 'ERROR_CODE_0'){
                        alert("Exercise successfully added.")
                        this.setState({
                                name: '',
                                image: null,
                                description: '',
                                burnedCaloriesEasy: '',
                                burnedCaloriesNormal: '',
                                burnedCaloriesHard: ''
                        });
                        axios.post('http://localhost:8080/addExercise/saveImage/'+ data.name , formData, {
                                    headers:{
                                        'content-type': 'multipart/form-data'
                                    }
                            }).then(response=>{
                                if(response.data.error_code === 'ERROR_CODE_0'){
                                    console.log("image saved")
                                }else{
                                    alert("Unable to save image.")
                                }
                        });
                        this.props.history.push('/'+localStorage.getItem('userName'))

                    }else{
                        alert("Adding exercise failed.");

                        this.setState({
                                name: '',
                                image: null,
                                description: '',
                                burnedCaloriesEasy: '',
                                burnedCaloriesNormal: '',
                                burnedCaloriesHard: ''

                        });
                    }
                });
    }


render() {
    return(
        <div className="container">
            <div className="row">
            <form onSubmit={this.handleSubmit} className="white">

            <h5 className="grey-text text-darken-3">Add New Exercise</h5>
            <div className="col s6 offset-s3">
                <div className="input-field" >
                    <input type="file" onChange={this.handleImageChange}/>
                </div>

                <div className="input-field">
                    <label htmlFor="name">Name</label>
                    <input type="text" id="name" onChange={this.handleNameChange} value={this.state.name}/>
                </div>

                <div className="input-field">
                    <label htmlFor="description">Description</label>
                     <input type="text" id="description" onChange={this.handleDescriptionChange} value={this.state.description}/> 
                </div>

                <div className="input-field">
                    <label htmlFor="burnedCaloriesEasy">Burned calories in easy mode(kcal)</label>
                     <input type="number" id="burnedCaloriesEasy" onChange={this.handleBurnedCaloriesEasy} value={this.state.burnedCaloriesEasy}/> 
                </div>

                <div className="input-field">
                    <label htmlFor="burnedCaloriesNormal">Burned calories in normal mode (kcal)</label>
                     <input type="number" id="burnedCaloriesNormal" onChange={this.handleBurnedCaloriesNormal} value={this.state.burnedCaloriesNormal}/> 
                </div>

                <div className="input-field">
                    <label htmlFor="burnedCaloriesHard">Burned calories in hard mode(kcal)</label>
                     <input type="number" id="burnedCaloriesHard" onChange={this.handleBurnedCaloriesHard} value={this.state.burnedCaloriesHard}/> 
                </div>

                <div className="input-field">
                     <button className="btn red lighten-1 z-depth-0">Add Exercise</button>
                </div>
                </div>

            </form>
            </div>
        </div>

    )
}

}

export default withRouter(AddExercise);