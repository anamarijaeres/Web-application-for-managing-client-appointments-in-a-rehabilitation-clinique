import React, {Component} from 'react'
import axios from 'axios'
import {NavLink, withRouter} from "react-router-dom";

class DoctorCoachProfile extends Component{
    state={
       user:'',
        reviewList:[],
        exercises: []

    }

    componentDidMount(){
    axios.get('http://localhost:8080/'+this.props.matchLink.match.params.username)
        .then(response=>{
         this.setState({
                      user:response.data
            })
        })
        axios.get('http://localhost:8080/reviewList/'+ this.props.matchLink.match.params.username)
            .then(response=>{
                this.setState({
                    reviewList:response.data
                })
            })

        axios.get('http://localhost:8080/getWorkouts/' + localStorage.getItem("userName"))
        .then(response=>{
            this.setState({
                exercises: response.data
             })
        })

    }
    render(){
        localStorage.setItem("usernameDocCoach", this.state.user.username)
        const reviews = this.state.reviewList;
        const revList = reviews.map(review => {
            return (

                <div className="post card" key={review.id}>

                    <div className="card-content">
                        <span className="card-title">User:{review.username}</span>
                        <p>{review.body}</p>

                    </div>
                </div>

            )
        })

        const exercises = this.state.exercises;
        const trainingList = exercises.map(exercise =>{
            return(
                <div className="post card" key={exercise.id}>
                    <div className="card-content">
                        <span className="card-title">{exercise.exerciseName}</span>
                        <p> Intensity : {exercise.mode} </p>
                        <p> Duration : {exercise.duration} min</p>
                    </div>
                </div>

            )
        });
        return(
        <div className="container">
            <div className="row">
                <div className="col s12 m4">
                    <div className="card">
                        <div className="card-image">
                            <img style={{width: 119, height: 84, left: "56%"}}
                                 src={"http://localhost:8080/img/" + this.state.username}
                                 alt=""/>
                            <span className="card-title blue darken-2">Profile Info</span>
                        </div>
                        <div className="card-content">
                            <div className="container">
                                <div className="container">
                                    <h6 className="center">Username: {this.state.user.username}</h6>
                                </div>
                                <div className="container">
                                    <h6 className="center">Firstname: {this.state.user.firstname}</h6>
                                </div>
                                <div className="container">
                                    <h6 className="center">Lastname: {this.state.user.lastname}</h6>
                                </div>
                                <div className="container">
                                    <h6 className="center">Role: {this.state.user.role}</h6>
                                </div>
                                <div className="container">
                                    <h6 className="center">Email: {this.state.user.email}</h6>
                                </div>
                                <div className="container">
                                    <h6 className="center">Max number of
                                        clients: {this.state.user.maxNumClient}</h6>
                                </div>
                            </div>
                        </div>
                        <div className="center card-action">
                            <NavLink to={"/"+ localStorage.getItem("userName")}>Back</NavLink>
                        </div>
                    </div>
                </div>

            <div className="col s12 m4">
                <div className="card blue darken-2">
                    <div className="card-content white-text">
                        <span className="card-title">Reviews:

                        </span>
                        <div className="center card-action">
                            <NavLink to={"/review"}>Add a review</NavLink>
                        </div>
                        <div className="containter black-text">
                            {revList}
                        </div>
                    </div>
                </div>
            </div>

                 <div className="col s12 m4">
                              <div className="card blue darken-2">
                                  <div className="card-content white-text">
                                      <span className="card-title">My Training: </span>
                                      <div className="containter black-text">
                                      {trainingList}
                                      </div>
                                  </div>
                              </div>
                          </div>


            </div>
         </div>
        )
    }

}

export default withRouter(DoctorCoachProfile);