import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';
import FormData from 'form-data'

class SignupForm extends Component{
    state={
        //role ti je u props
        formClient:{
            firstName:'',
            lastName:'',
            userName:'',
            password:''
        },
        formDC:{
            approvedByAdmin:false,
            firstName:'',
            lastName:'',
            userName:'',
            password:'',
            image: null,
            email:'',
            maxBr:''
        }
    }

    //CLIENT handlers

    handleFirstNameChangeClient=(e)=>{
        this.setState({formClient:{...this.state.formClient, firstName:e.target.value}})
    }

    handleLastNameChangeClient=(e)=>{
        this.setState({formClient:{...this.state.formClient, lastName:e.target.value}})
    }

    handleUserNameChangeClient= (e) => { 
        this.setState({formClient:{...this.state.formClient, userName:e.target.value}})
    }

    handlePasswordChangeClient= (e) => {
        this.setState({formClient:{...this.state.formClient, password:e.target.value}})
    }

    handleSubmitClient=(e)=>{
        e.preventDefault();
        const data={
            firstname: this.state.formClient.firstName,
            lastname: this.state.formClient.lastName,
            username: this.state.formClient.userName,
            password: this.state.formClient.password
        };
        return axios.post('http://localhost:8080/registerClient', data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0'){
                    alert("You're successfully registered. Please login to continue!")
                    this.setState({formClient:{firstName:'',
                                                lastName:'',
                                                userName:'',
                                                password:''}});
                    this.props.history.push('/login')
                }else{
                    alert("Username \"" +response.data.username +"\" is taken.")
                    this.setState({formClient:{firstName:'',
                                                lastName:'',
                                                userName:'',
                                                password:''}});
                }
            });
    }

    //DOCTOR or COACH handlers

    handleFirstNameChangeDC=(e)=>{
        this.setState({formDC:{...this.state.formDC, firstName:e.target.value}})
    }

    handleLastNameChangeDC=(e)=>{
        this.setState({formDC:{...this.state.formDC, lastName:e.target.value}})
    }

    handleUserNameChangeDC= (e) => { 
        this.setState({formDC:{...this.state.formDC, userName:e.target.value}})
    }

    handlePasswordChangeDC= (e) => {
        this.setState({formDC:{...this.state.formDC, password:e.target.value}})
    }

    handleImageChangeDC=(e)=>{
        this.setState({formDC:{...this.state.formDC, image:e.target.files[0]}})
    }

    handleEmailChangeDC= (e) => {
        this.setState({formDC:{...this.state.formDC, email:e.target.value}})
    }

    handleMaxBrChangeDC= (e) => {
        this.setState({formDC:{...this.state.formDC, maxBr:e.target.value}})
    }

    handleSubmitDC=(e)=>{
        e.preventDefault();
        const data={
            approvedByAdmin: this.state.formDC.approvedByAdmin,
            firstname: this.state.formDC.firstName,
            lastname: this.state.formDC.lastName,
            username: this.state.formDC.userName,
            password: this.state.formDC.password,
            role: this.props.role,
            email: this.state.formDC.email,
            maxNumClient: this.state.formDC.maxBr
        };

        let formData=new FormData();
        formData.append('imageFile', this.state.formDC.image);

        return axios.post('http://localhost:8080/registerDoctorCoach', data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0'){
                    alert("You're successfully registered. Please wait while an administrator approves your account.")
                    this.setState({formDC:{
                        approvedByAdmin:false,
                        firstName:'',
                        lastName:'',
                        userName:'',
                        password:'',
                        image: null,
                        email:'',
                        maxBr:''
                    }});
                    axios.post('http://localhost:8080/saveImage/'+data.username, formData, {
                                headers:{
                                    'content-type': 'multipart/form-data' 
                                }
                        }).then(response=>{
                            if(response.data.error_code === 'ERROR_CODE_0'){
                                console.log("image saved")
                            }else{
                                alert("Unable to save image for \"" +response.data.username +"\".")
                            }
                    });
                    this.props.history.push('/login')
                }else{
                    alert("Username \"" +response.data.username +"\" is taken.")
                    this.setState({formDC:{
                        approvedByAdmin:false,
                        firstName:'',
                        lastName:'',
                        userName:'',
                        password:'',
                        image: null,
                        email:'',
                        maxBr:''
                    }});
                } 
            });         
    }

    render(){
        if(this.props.role==='Client'){
            return(
                <div className="container">
                    <form onSubmit={this.handleSubmitClient} className="white">
                        <h5 className="grey-text text-darken-3">Client registration</h5>
                        <div className="input-field">
                            <label htmlFor="firstName">First name</label>
                            <input type="text" id="firstName" onChange={this.handleFirstNameChangeClient} value={this.state.formClient.firstName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="lastName">Last name</label>
                            <input type="text" id="lastName" onChange={this.handleLastNameChangeClient} value={this.state.formClient.lastName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="username">Username</label>
                            <input type="text" id="userName" onChange={this.handleUserNameChangeClient} value={this.state.formClient.userName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="password">Password</label>
                            <input type="password" id="password" onChange={this.handlePasswordChangeClient} value={this.state.formClient.password}/>
                        </div>
                        <div className="input-field">
                            <button className="btn red lighten-1 z-depth-0">Sign up</button>
                        </div>
                    </form>
                </div>
            )
        }else if(this.props.role===''){
            return(
                <div className="container">
                    <p className="center"></p>
                </div>
            )
        }else{
            return(
                <div className="container">
                    <form onSubmit={this.handleSubmitDC} className="white">
                        <h5 className="grey-text text-darken-3">Doctor or Coach registration</h5>
                        <div className="input-field">
                            <label htmlFor="firstName">First name</label>
                            <input type="text" id="firstName" onChange={this.handleFirstNameChangeDC} value={this.state.formDC.firstName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="lastName">Last name</label>
                            <input type="text" id="lastName" onChange={this.handleLastNameChangeDC} value={this.state.formDC.lastName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="username">Username</label>
                            <input type="text" id="userName" onChange={this.handleUserNameChangeDC} value={this.state.formDC.userName}/> 
                        </div>
                        <div className="input-field">
                            <label htmlFor="password">Password</label>
                            <input type="password" id="password" onChange={this.handlePasswordChangeDC} value={this.state.formDC.password}/>
                        </div>
                        <div className="input-field"> 
                            <input type="file" onChange={this.handleImageChangeDC}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="email">Email</label>
                            <input type="email" id="email" onChange={this.handleEmailChangeDC} value={this.state.formDC.email}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="maxBr">Maksimalni broj klijenata</label>
                            <input type="number" id="maxBr" onChange={this.handleMaxBrChangeDC} value={this.state.formDC.maxBr}/> 
                        </div>
                        <div className="input-field">
                            <button className="btn red lighten-1 z-depth-0">Sign up</button>
                        </div>
                    </form>
                </div>
            )
        }
    }
}

export default withRouter(SignupForm);