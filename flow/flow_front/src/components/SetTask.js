import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class SetTask extends Component {

state={

    flag:false,
    exercises:[],
    list:[],
    name:'',
    mode:'',
    duration:''
}

componentDidMount(){
    axios.get('http://localhost:8080/setTask/'+ this.props.matchLink.match.params.clientUsername)
    .then(response => {
        this.setState({
            flag: response.data.flag,
            training: response.data.training

        });
    })

    axios.get('http://localhost:8080/getExercises')
    .then(response=>{
        this.setState({
                list: response.data

        });
    })
}

 handleSubmit = (e) => {
    e.preventDefault();
    const data= {
    username: this.props.matchLink.match.params.clientUsername,
    exerciseName: this.state.name,
    mode: this.state.mode,
    duration: this.state.duration };

    return axios.post('http://localhost:8080/addWorkout', data)
    .then(response=>{
    alert("Workout successfully added!");
    this.setState({
        name:'',
        mode:'',
        duration:''

    })

    });

 }

 handleNameChange=(e)=> {
         this.setState({
            name: e.target.value
         })
 }

 handleModeChange=(e)=> {
        this.setState({
        mode: e.target.value
        })
 }

 handleDurationChange=(e)=> {
         this.setState({
         duration: e.target.value
         })
  }




render(){
    if(this.state.flag === false){
    const workouts = this.state.list;
    const exerciseList= workouts.map((workout)=>{
                            return(
                                <option value={workout} >{workout}</option>
                            )
                        });

        return (
            <div className="container">
                        <form onSubmit={this.handleSubmit} className="white">
                            <div className="input-field col s12">
                                <select className="browser-default" onChange={this.handleNameChange}>
                                <option value="" disabled selected>Select exercise:</option>
                                  {exerciseList}
                                </select>
                            </div>


                            <div className="input-field col s12">
                                  <select className="browser-default" onChange={this.handleModeChange}>
                                  <option value="" disabled selected>Select intensity:</option>
                                    <option value="Easy">Easy</option>
                                    <option value="Normal">Normal</option>
                                    <option value="Hard">Hard</option>
                                   </select>
                            </div>

                            <div className="input-field">
                            <label htmlFor="mode">Set duration in minutes:</label>
                                   <input type="number" id="duration" onChange={this.handleDurationChange} value={this.state.duration}/>
                            </div>

                            <div className="input-field">
                                <button className="btn red lighten-1 z-depth-0" >Submit</button>
                            </div>
                        </form>
            </div>
       )
            
    }else{
            return(

                <h3 className="center"> Already given. </h3>

            )
     }

    }


   }

export default withRouter(SetTask);