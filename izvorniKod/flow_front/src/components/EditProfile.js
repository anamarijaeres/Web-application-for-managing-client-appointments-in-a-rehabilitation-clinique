import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class EditProfile extends Component {
    state={
        formClient:{
            firstName:'',
            lastName:'',
            userName:'',
            password:''
        },
        formDC:{
            approvedByAdmin:true,
            firstName:'',
            lastName:'',
            userName:'',
            password:'',
            image: null,
            email:'',
            maxBr:''
        }
    }

    //CLIENT data change handlers

    handleFirstNameChangeClient=(e)=>{
        this.setState({formClient:{...this.state.formClient, firstName:e.target.value}})
    }

    handleLastNameChangeClient=(e)=>{
        this.setState({formClient:{...this.state.formClient, lastName:e.target.value}})
    }

    handlePasswordChangeClient= (e) => {
        this.setState({formClient:{...this.state.formClient, password:e.target.value}})
    }

    handleSubmitClientEdit=(e)=>{
        e.preventDefault();
        const data={
            firstname: this.state.formClient.firstName,
            lastname: this.state.formClient.lastName,
            username: localStorage.getItem('userName'),
            password: this.state.formClient.password
        };
        return axios.post('http://localhost:8080/'+localStorage.getItem('userName')+'/'+localStorage.getItem('role')+'/editProfile', data)
            .then(response=>{
                    if(response.data.error_code === 'ERROR_CODE_0'){
                        alert(response.data.message)
                    }
                }
            )};

    //DOCTOR or COACH handlers

    handleFirstNameChangeDC=(e)=>{
        this.setState({formDC:{...this.state.formDC, firstName:e.target.value}})
    }

    handleLastNameChangeDC=(e)=>{
        this.setState({formDC:{...this.state.formDC, lastName:e.target.value}})
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

    handleSubmitDCEdit=(e)=>{
        e.preventDefault();
        const data={
            approvedByAdmin: this.state.formDC.approvedByAdmin,
            firstname: this.state.formDC.firstName,
            lastname: this.state.formDC.lastName,
            username: localStorage.getItem('userName'),
            password: this.state.formDC.password,
            email: this.state.formDC.email,
            maxNumClient: this.state.formDC.maxBr
        };

        let formData=new FormData();
        formData.append('imageFile', this.state.formDC.image);

        return axios.post('http://localhost:8080/'+localStorage.getItem('userName')+'/DoctorCoach'+'/editProfile', data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0'){
                    alert(response.data.message)
                    if(this.state.formDC.image!==null){
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
                    }
                }
                this.props.history.push('/'+localStorage.getItem('userName'))
            });
    }

    componentDidMount(){
        axios.get('http://localhost:8080/'+localStorage.getItem('userName'))
            .then(response=>{
                if(localStorage.getItem('role')==='Client'){
                    this.setState({formClient:{...this.state.formClient, firstName:response.data.firstname}})
                    this.setState({formClient:{...this.state.formClient, lastName:response.data.lastname}})
                    this.setState({formClient:{...this.state.formClient, password:response.data.password}})
                }else{
                    this.setState({formDC:{...this.state.formDC, firstName:response.data.firstname}})
                    this.setState({formDC:{...this.state.formDC, lastName:response.data.lastname}})
                    this.setState({formDC:{...this.state.formDC, password:response.data.password}})
                    this.setState({formDC:{...this.state.formDC, email:response.data.email}})
                    this.setState({formDC:{...this.state.formDC, maxBr:response.data.maxNumClient}})
                }
                
            })
    }

    render(){
        if(localStorage.getItem('role')==='Client'){
            return(
                <div className="container">
                    <form onSubmit={this.handleSubmitClientEdit} className="white">
                        <label htmlFor="firstName">First name</label>
                        <input type="text" id="firstName" onChange={this.handleFirstNameChangeClient} defaultValue={this.state.formClient.firstName}/>
                        <label htmlFor="lastName">Last name</label>
                        <input type="text" id="lastName" onChange={this.handleLastNameChangeClient} defaultValue={this.state.formClient.lastName}/>
                        <label htmlFor="password">Password</label>
                        <input type="text" id="password" onChange={this.handlePasswordChangeClient} defaultValue={this.state.formClient.password}/>
                        <div className="input-field">
                            <button className="btn red lighten-1 z-depth-0">Save</button>
                        </div>
                    </form>
                </div>
            )
        }else{
            return(
                <div className="container">
                    <form onSubmit={this.handleSubmitDCEdit} className="white">
                        <label htmlFor="firstName">First name</label>
                        <input type="text" id="firstName" onChange={this.handleFirstNameChangeDC} defaultValue={this.state.formDC.firstName}/>
                        <label htmlFor="lastName">Last name</label>
                        <input type="text" id="lastName" onChange={this.handleLastNameChangeDC} defaultValue={this.state.formDC.lastName}/>
                        <label htmlFor="password">Password</label>
                        <input type="text" id="password" onChange={this.handlePasswordChangeDC} defaultValue={this.state.formDC.password}/>
                        <div className="input-field"> 
                            <input type="file" onChange={this.handleImageChangeDC}/>
                        </div>
                        <label htmlFor="email">Email</label>
                        <input type="email" id="email" onChange={this.handleEmailChangeDC} value={this.state.formDC.email}/>
                        <label htmlFor="maxBr">Maksimalni broj klijenata</label>
                        <input type="number" id="maxBr" onChange={this.handleMaxBrChangeDC} value={this.state.formDC.maxBr}/> 
                        <div className="input-field">
                            <button className="btn red lighten-1 z-depth-0">Save</button>
                        </div>
                    </form>
                </div>
            )
        }
    }
}

export default withRouter(EditProfile);