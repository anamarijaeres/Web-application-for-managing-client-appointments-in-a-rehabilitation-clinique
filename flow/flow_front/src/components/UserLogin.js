import React, {Component} from 'react'
import axios from 'axios'
import DoctorCoachList from "./DoctorCoachList";
import {Link, NavLink} from 'react-router-dom'
import AddProductCategory from './AddProductCategory'
import AddExercise from './AddExercise'
import SetTask from './SetTask'
import PrescribeDiet from "./PrescribeDiet";

class UserLogin extends Component{
    //username ti je u this.props.matchLink.match.params.username;

    state={
        user:'',
        adminList:[],
        requestList: [],
        cooperationsList:[],
        cooperationDoctor:'',
        cooperationCoach:'',
        reviewList: []

    }

    componentDidMount(){
        const role=localStorage.getItem('role')
        axios.get('http://localhost:8080/'+this.props.matchLink.match.params.username)
            .then(response=>{
                this.setState({
                    user:response.data
                })
            })
        
        axios.get('http://localhost:8080/adminList')
            .then(response=>{
                this.setState({
                    adminList:response.data
                })
            })

        if(role !== 'Client') {
            axios.get('http://localhost:8080/requests/' + this.props.matchLink.match.params.username)
                .then(response => {
                    this.setState({
                        requestList: response.data
                    })
                })
            axios.get('http://localhost:8080/cooperations/' + this.props.matchLink.match.params.username)
                .then(response => {
                    this.setState({
                        cooperationsList: response.data
                    })
                })
            axios.get('http://localhost:8080/reviewList/' + this.props.matchLink.match.params.username)
                .then(response => {
                    this.setState({
                        reviewList: response.data
                    })
                })

        }else {

            axios.get('http://localhost:8080/cooperationDoctor/' + this.props.matchLink.match.params.username)
                .then(response => {
                    this.setState({
                        cooperationDoctor: response.data
                    })
                })
            axios.get('http://localhost:8080/cooperationCoach/' + this.props.matchLink.match.params.username)
                .then(response => {
                    this.setState({
                        cooperationCoach: response.data
                    })
                })

        }

    }

    approveRow(username, e, id){
        e.preventDefault();
        axios.post('http://localhost:8080/approve/'+username)
            .then(response=>{
                const posts=this.state.adminList.filter(post=>{
                    return post.id!==id
                })
                this.setState({
                    adminList:posts
                })
            })
    }

    deleteRow(username, e, id){
        e.preventDefault();
        axios.post('http://localhost:8080/delete/'+username)
            .then(response=>{
                const posts=this.state.adminList.filter(post=>{
                    return post.id!==id
                })
                this.setState({
                    adminList:posts
                })
            })
    }
    approveRequest(username, e, id) {
        e.preventDefault();
        axios.post('http://localhost:8080/approveRequest/'+username ,this.state.user.username)
            .then(response=>{
                const posts=this.state.requestList.filter(post=>{
                    return post.id!==id
                })
                this.setState({
                    requestList:posts

                })
            })

    }

    deleteRequest(username, e, id) {
        e.preventDefault();
        axios.post('http://localhost:8080/deleteRequest/'+username,this.state.user.username)
            .then(response=>{
                const posts=this.state.requestList.filter(post=>{
                    return post.id!==id
                })
                this.setState({
                    requestList:posts
                })
            })
    }

    breakCooperation(username,e,id){
        e.preventDefault();
        axios.post('http://localhost:8080/breakCooperation/'+username,this.state.user.username)
            .then(response=>{
                const posts=this.state.cooperationsList.filter(post=>{
                    return post.id!==id
                })
                this.setState({
                    cooperationsList:posts
                })

            })
    }
    breakCooperationDoctor(username,e,id){
        e.preventDefault();
        axios.post('http://localhost:8080/breakCooperationDoctor/'+username,this.state.user.username)
            .then(response=>{
            this.setState({
                cooperationDoctor:''
            })
        })
    }
    breakCooperationCoach(username,e,id){
        e.preventDefault();
        axios.post('http://localhost:8080/breakCooperationCoach/'+username,this.state.user.username)
            .then(response=>{
            this.setState({
                cooperationDoctor:''
            })
        })
    }

    render(){
        const role=localStorage.getItem('role')
        const userName=localStorage.getItem('userName')

        if(role==='Client'){

            if(userName==='ADMIN'){
                const posts=this.state.adminList;

                const postList=posts.map(post=>{
                    return(
                        <div className="post card" key={post.id}>
                            <div className="card-content">
                                <span className="card-title">{post.username}</span>
                                <p>{post.body}</p>
                                <button className="btn red lighten-1 z-depth-0" onClick={(e) => this.approveRow(post.username, e, post.id)}>Yes</button>
                                <button className="btn red lighten-1 z-depth-0" onClick={(e) => this.deleteRow(post.username, e, post.id)}>No</button>
                            </div>
                        </div>
                    )
                })

                return(
                    <div className="container">
                        <div className="row">
                            <div className="col s12 m6">
                                <div className="card center">

                                    <div className="card-content">
                                    <span className="card-title blue darken-2 white-text center">Profile Info</span>
                                            <div className="container">
                                               <div className="collapsible-header">
                                                    <i class="material-icons">person_pin</i>
                                                    <div>{this.state.user.username}</div>
                                               </div>

                                               <div className="collapsible-header ">
                                                    <i class="material-icons">person</i>
                                                     <div >{this.state.user.firstname} {this.state.user.lastname}</div>
                                                </div>

                                                <div className="collapsible-header ">
                                                    <i class="material-icons">work</i>
                                                    <div>Admin</div>
                                                 </div>
                                            </div>
                                          </div>
                                          <NavLink to={localStorage.getItem('userName')+"/editProfile"}>Edit Profile</NavLink>
                                </div>

                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/addProductCategory" className="card-title white-text">add category</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/addProduct" className="card-title white-text">add product</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to= {"/"+this.state.user.username+"/addExercise"} className="card-title white-text">add exercise</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/editProduct" className="card-title white-text">edit product</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/editProductCategory" className="card-title white-text">edit category</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/deleteCategory" className="card-title white-text">remove category</NavLink></li>
                                </div>
                                <div className="card red lighten-1">
                                    <li className="center"><NavLink to="/deleteProduct" className="card-title white-text">remove product</NavLink></li>
                                </div>
                               <div className="card red lighten-1">
                                    <li className="center"><NavLink to= {"/editExercise"} className="card-title white-text">edit exercise</NavLink></li>
                               </div>
                               <div className="card red lighten-1">
                                   <li className="center"><NavLink to= {"/removeExercise"} className="card-title white-text">remove exercise</NavLink></li>
                              </div>

                            </div>

                            <div className="col s12 m6">
                                <div className="card blue darken-2">
                                    <div className="card-content white-text">
                                        <span className="card-title">Registrations for approve:</span>
                                        <div className="container black-text">
                                            {postList}
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                )
            } else {

                return (
                    <div className="container">
                        <div className="row">
                            <div className="col s12 m6">
                                <div className="card center">

                                    <div className="card-content">
                                        <span className="card-title blue darken-2 white-text center">Profile Info</span>
                                        <div className="container">
                                            <div className="collapsible-header">
                                                <i class="material-icons">person_pin</i>
                                                <div>{this.state.user.username}</div>
                                            </div>

                                            <div className="collapsible-header ">
                                                <i class="material-icons">person</i>
                                                <div >{this.state.user.firstname} {this.state.user.lastname}</div>
                                            </div>

                                            <div className="collapsible-header ">
                                                <i class="material-icons">work</i>
                                                <div>Client</div>
                                            </div>
                                        </div>
                                        <NavLink to={localStorage.getItem('userName')+"/editProfile"}>Edit Profile</NavLink>
                                    </div>
                                </div>
                                <div className="container" style={{padding: 5}}>
                                    <div className="center card-action">
                                        <div className="btn red lighten-1 z-depth-0" >
                                            <NavLink to={"/"+localStorage.getItem('userName')+"/addProduct"} style={{color: 'white'}} >ADD PRODUCT</NavLink>
                                        </div>
                                    </div>
                                </div>
                                <div className="container" style={{padding: 5}}>
                                    <div className="center card-action">
                                        <div className="btn red lighten-1 z-depth-0" >
                                            <NavLink to={"/"+localStorage.getItem('userName')+"/statistic"} style={{color: 'white'}} >STATISTIC</NavLink>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div className="col s12 m6">
                                <div className="post card" key={this.state.cooperationDoctor.id}>
                                    <div className="card-content">
                                        <span className="card-title">My doctor:</span>
                                        <p>{this.state.cooperationDoctor.body}</p>
                                        <button className="btn red lighten-1 z-depth-0"
                                                onClick={(e) => this.breakCooperationDoctor(this.state.cooperationDoctor.username, e, this.state.cooperationDoctor.id)}>
                                            End collaboration
                                        </button>
                                        <div className="center card-action">
                                            <NavLink to={"/profile/"+ this.state.cooperationDoctor.username}>Profile</NavLink>
                                        </div>
                                    </div>
                                </div>

                                <div className="post card" key={this.state.cooperationCoach.id}>
                                    <div className="card-content">
                                        <span className="card-title">My coach:</span>
                                        <p>{this.state.cooperationCoach.body}</p>
                                        <button className="btn red lighten-1 z-depth-0"
                                                onClick={(e) => this.breakCooperationCoach(this.state.cooperationCoach.username, e, this.state.cooperationCoach.id)}>
                                            End collaboration
                                        </button>
                                        <div className="center card-action">
                                            <NavLink to={"/coach/"+this.state.cooperationCoach.username}>Profile</NavLink>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <DoctorCoachList username={this.state.user.username} firstName={this.state.user.firstName} lastName={this.state.user.lastName} role={role} />

                    </div>
                )

            }
       } else {
                const cooperations=this.state.cooperationsList;
                let coopList=null;
                const requests = this.state.requestList;
                let buttonList=null;
                const requestsList = requests.map(request => {
                    return (

                        <div className="post card" key={request.id}>

                            <div className="card-content">
                                <span className="card-title">{request.username}</span>
                                <p>{request.body}</p>
                                <button className="btn red lighten-1 z-depth-0"
                                        onClick={(e) => this.approveRequest(request.username, e, request.id)}>Approve
                                </button>
                                <button className="btn red lighten-1 z-depth-0"
                                        onClick={(e) => this.deleteRequest(request.username, e, request.id)}>Decline
                                </button>
                            </div>
                        </div>

                    )
                })
            const reviews = this.state.reviewList;
            const revList = reviews.map(review => {
                return (

                    <div className="post card" key={review.id}>

                        <div className="card-content">
                            <span className="card-title">User:{review.username}</span>
                            <p>{review.body}</p>
                            <div className="center card-action">
                                <NavLink  to={"/reply/" +review.username+"/"+review.id+"/"+this.state.user.username}>Reply</NavLink>
                            </div>

                        </div>
                    </div>

                )
            })




            if (role === 'Doctor') {

                coopList = cooperations.map(cooperation => {
                    return (

                        <div className="post card" key={cooperation.id}>

                            <div className="card-content">
                                <span className="card-title">{cooperation.username}</span>
                                <p>{cooperation.body}</p>
                                <button className="btn red lighten-1 z-depth-0"
                                        onClick={(e) => this.breakCooperation(cooperation.username, e, cooperation.id)}>
                                    End collaboration
                                </button>
                                <div className="row">
                                    <div className="col s10">
                                        <div className="btn red lighten-1 z-depth-0" >
                                            <NavLink to={"/"+cooperation.username+"/statistic"} style={{color: 'white'}} >STATISTIC</NavLink>
                                        </div>
                                    </div>
                                </div>
                               <div className="btn red lighten-1 z-depth-0" >
                                    <NavLink to={"/"+localStorage.getItem('userName')+"/PrescribeDiet/"+cooperation.username} style={{color: 'white'}} name= {cooperation.username}>Prescribe Diet</NavLink>
                                </div>
                                {/*<button className="btn red lighten-1 z-depth-0" onClick={(e) => console.log("okej")}>
                                    Prescribe diet
                                </button>*/}

                            </div>
                        </div>
                    )
                })

                 buttonList=<div>
                       <div className="card red lighten-1">
                            <li className="center"><NavLink to="/addProductCategory" className="card-title white-text">add category</NavLink></li>
                       </div>
                        <div className="card red lighten-1">
                             <li className="center"><NavLink to="/addProduct" className="card-title white-text">add product</NavLink></li>
                        </div>


                 </div>

            }else {
                coopList = cooperations.map(cooperation => {
                    return (

                        <div className="post card" key={cooperation.id}>

                            <div className="card-content">
                                <span className="card-title">{cooperation.username}</span>
                                <p>{cooperation.body}</p>
                                <button className="btn red lighten-1 z-depth-0"
                                        onClick={(e) => this.breakCooperation(cooperation.username, e, cooperation.id)}>
                                    End collaboration
                                </button>

                                <div className="btn red lighten-1 z-depth-0" >
                                    <NavLink to={"/"+localStorage.getItem('userName')+"/setTask/"+cooperation.username} style={{color: 'white'}} name= {cooperation.username}>SET TASK</NavLink>
                                </div>

                            </div>
                        </div>
                    )
                })

                buttonList=<div>

                    <div className="card red lighten-1">
                        <li className="center"><NavLink to= {"/"+this.state.user.username+"/addExercise"} className="card-title white-text">add exercise</NavLink></li>
                    </div>
                </div>
            }
        return(
            <div className="container">
                <div className="row">
                    <div className="col s12 m4">
                        <div className="card">
                            <div className="card-image">
                                <img style={{width:121, height:84, left: "56%"}} src={"http://localhost:8080/img/"+this.props.matchLink.match.params.username} alt=""/>
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
                                                <h6 className="center">Role: {role}</h6>
                                            </div>
                                            <div className="container">
                                                <h6 className="center">Email: {this.state.user.email}</h6>
                                            </div>
                                            <div className="container">
                                                <h6 className="center">Max number of
                                                    clients: {this.state.user.maxNumClient}</h6>
                                            </div>
                                            <div className="card-action">
                                                <NavLink to={"/" + this.state.user.username + "/editProfile"}>Edit
                                                    Profile</NavLink>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col s12 m4">
                                <div className="card blue darken-2">
                                    <div className="card-content white-text">
                                        <span className="card-title">Requests for collaboration:</span>
                                        <div className="containter black-text">
                                            {requestsList}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col s12 m4">
                                <div className="card blue darken-2">
                                    <div className="card-content white-text">
                                        <span className="card-title">My clients:</span>
                                        <div className="containter black-text">
                                            {coopList}
                                        </div>

                                    </div>
                                </div>
                            </div>


                        </div>

                        <div className="col s12 m4">
                            <div className="card blue darken-2">
                                <div className="card-content white-text">
                                    <span className="card-title">Reviews:</span>
                                    <div className="containter black-text">
                                        {revList}
                                    </div>
                                </div>

                        </div>


                         <div>

                           {buttonList}
                         </div>
            </div>
       </div>

                )
        }
    }

}

export default UserLogin;