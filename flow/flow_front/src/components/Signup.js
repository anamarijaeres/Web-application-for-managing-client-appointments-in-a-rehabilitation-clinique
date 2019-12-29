import React, { Component } from 'react'
import SignupForm from './SignupForm'

class Signup extends Component {
    state={
        role: ''
    }

    handleChange=(e)=> {
        this.setState({
            role:e.target.value
        })
    }

    handleSubmit=(e)=> {
        e.preventDefault();
    }
    
    render(){
        return(
            <div className="container">
            <form onSubmit={this.handleSubmit}>
                <div className="input-field col s12">
                    <select className="browser-default" value={this.state.value} onChange={this.handleChange}>
                        <option value="" disabled selected>Please choose your role:</option>
                        <option value="Client">Client</option>
                        <option value="Doctor">Doctor</option>
                        <option value="Coach">Coach</option>
                    </select>
                </div>
            </form>
            <div className="container">
                <SignupForm role={this.state.role}/>
            </div>
            </div>
            
        )
    }
}

export default Signup
