import React, {Component} from 'react'
import axios from 'axios'
import {NavLink, withRouter} from "react-router-dom";

class DoctorProfile extends Component{
    state={
       user:'',
        reviewList:[]


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
                                    <div className="collapsible-header">
                                         <i class="material-icons">person_pin</i>
                                         <div>{this.state.user.username}</div>
                                    </div>

                                    <div className="collapsible-header">
                                         <i class="material-icons">person</i>
                                          <div >{this.state.user.firstname} {this.state.user.lastname}</div>
                                     </div>

                                     <div className="collapsible-header">
                                         <i class="material-icons">work</i>
                                         <div >{this.state.user.role}</div>
                                      </div>

                                      <div className="collapsible-header">
                                         <i class="material-icons">email</i>
                                        <div >{this.state.user.email}</div>
                                       </div>

                                       <div className="collapsible-header">
                                          <i class="material-icons">people</i>
                                          <div >{this.state.user.maxNumClient}</div>
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


            </div>
         </div>
        )
    }

}

export default withRouter(DoctorProfile);