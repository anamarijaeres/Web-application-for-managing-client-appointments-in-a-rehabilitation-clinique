import React, {Component} from 'react'
import axios from 'axios'
import {NavLink} from 'react-router-dom'

class UserLogin extends Component{
    //username ti je u this.props.matchLink.match.params.username;

    state={
        user:'',
        adminList:[]
    }

    componentDidMount(){
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
                                <div className="card blue-grey darken-1">
                                    <div className="card-content white-text">
                                        <span className="card-title">Profile Info</span>
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
                                                    <h6 className="center">Role: Admin</h6>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="container">
                            Registrations for approve:
                            {postList}
                        </div>
                    </div>
                )
            }else{

                return(
                    <div className="container">
                        <div className="row">
                            <div className="col s12 m6">
                                <div className="card blue-grey darken-1">
                                    <div className="card-content white-text">
                                        <span className="card-title">Profile Info</span>
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
                                                <div className="card-action">
                                                    <NavLink to={"/"+this.state.user.username+"/editProfile"}>Edit Profile</NavLink>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )
            }
       }else{
           return(
               <div className="container">
               <div className="row">
                    <div className="col s12 m4">
                        <div className="card">
                            <div className="card-image">
                            <img style={{width:150, height:150, left: "55%"}} src={"http://localhost:8080/img/"+this.props.matchLink.match.params.username} alt=""/>
                            <span className="card-title grey">Profile Info</span>
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
                                    <h6 className="center">Max number of clients: {this.state.user.maxNumClient}</h6>
                                </div>
                                <div className="card-action">
                                    <NavLink to={"/"+this.state.user.username+"/editProfile"}>Edit Profile</NavLink>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
               </div>
           )
       }
    }
}

export default UserLogin;