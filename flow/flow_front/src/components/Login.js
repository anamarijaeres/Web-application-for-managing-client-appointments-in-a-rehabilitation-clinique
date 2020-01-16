import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class Login extends Component {
    state={
        userName: '',
        password: ''
    }

    handleUserNameChange = (e) => {
        this.setState({userName: e.target.value})
    }

    handlePasswordChange = (e) => {
        this.setState({password: e.target.value})
    }

    handleSubmit = (e) =>  {
        e.preventDefault();
        axios.post('http://localhost:8080/login?username=' + this.state.userName + '&password=' + this.state.password)
          .then(response => {
              if(response.data.error_code === 'ERROR_CODE_4'){
                alert("Admin needs to approve your registration. Please wait.")
                this.setState({
                  userName: '',
                  password: ''
                });
              }else if(response.data.error_code === 'ERROR_CODE_1'){
                alert("Wrong username or password")
                this.setState({
                  userName: '',
                  password: ''
                });
              }else if(response.data.error_code === 'ERROR_CODE_0'){
                    this.props.handleLogin(this.state.userName, true, response.data.userRole);
                    this.setState({
                        userName: '',
                        password: ''
                      });
                      this.props.history.push('/'+localStorage.getItem('userName'));
              }
          });
    }

    render() {
        return (
            <div className="container">

                <form onSubmit={this.handleSubmit} className="white">
                    <h5 className="grey-text text-darken-3">Log In</h5>
                    <div className="input-field">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="userName" onChange={this.handleUserNameChange} value={this.state.userName}/> {/*type je email da field ima ova bolja svojstva */}
                    </div>
                    <div className="input-field">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" onChange={this.handlePasswordChange} value={this.state.password}/>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Login</button>
                    </div>
                </form>

            </div>
        )
    }
}

export default withRouter(Login)
