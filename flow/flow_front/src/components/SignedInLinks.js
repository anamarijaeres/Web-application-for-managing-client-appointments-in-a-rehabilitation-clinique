import React, { Component } from 'react'
import {NavLink} from 'react-router-dom'

class SignedInLinks extends Component{
    handleLogOut(){
        localStorage.setItem('userName', '');
        localStorage.setItem('loggedIn', false);
        localStorage.setItem('role', '');
    }

    render(){
        const firstLetter=localStorage.getItem('userName').substring(0,1);
        return (
            <ul className="right">
                <li><NavLink to="/" onClick={this.handleLogOut}>Log Out</NavLink></li>
                <li><NavLink to="/" className='btn btn-floating red lighten-1'>{firstLetter}</NavLink></li>
           </ul>
        )
    }
}

export default SignedInLinks
