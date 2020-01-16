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
    duration: this.state.duration
    };

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

 handleDone = (e) => {
      e.preventDefault();
          const data= {
          username: this.props.matchLink.match.params.clientUsername,
          exerciseName: this.state.name,
          mode: this.state.mode,
          duration: this.state.duration
          };

          return axios.post('http://localhost:8080/addWorkout', data)
          .then(response=>{
          alert("Workout successfully added!");
          this.setState({
              name:'',
              mode:'',
              duration:''
            })
          });

      window.location.reload();
 }

 handleBack = (e) => {

    this.props.history.push('/'+localStorage.getItem('userName'))
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
            <div className="container" >

                            <div className="input-field col s12">
                                <select className="browser-default" onChange={this.handleNameChange} value= {this.state.name}>
                                <option value="" disabled selected>Select exercise:</option>
                                  {exerciseList}
                                </select>
                            </div>

                            <div className="input-field col s12">
                                  <select className="browser-default" onChange={this.handleModeChange} value = {this.state.mode}>
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

                            <div className="row">
                               <a class="btn-floating btn-large waves-effect waves-light red" onClick = {this.handleSubmit}><i class="material-icons">add</i></a>
                               &nbsp;&nbsp;&nbsp;
                                <a class="btn-floating btn-large waves-effect waves-light green"onClink = {this.handleDone}><i class="material-icons">check</i></a>
                            </div>

            </div>
       )
            
    }else{
            return(
            <div className="container">
            <div className="center">
               <div className="post card">
               <div className="card-title">
                <h3 className="center"> Successfully added. </h3>
                </div>

                <div className="card-content">
                <a class="btn-floating btn-large waves-effect waves-light green" onClick={this.handleBack} ><i class="material-icons">check</i></a>
                </div>
                </div>
             </div>
             </div>
            )
     }

    }


   }

export default withRouter(SetTask);