import React from 'react'
import {withRouter} from 'react-router-dom' 
import SingedInLinks from './SignedInLinks'
import SingedOutLinks from './SignedOutLinks'

const Navbar=()=>{
    const status=localStorage.getItem('loggedIn')
    if(status==='true'){
        return (
            <nav className="nav-wrapper blue darken-2">
                <div className="container">
                    <a className="brand-logo">Poliklinika za rehabilitaciju</a> 
                    <SingedInLinks/>
                </div>
            </nav>
        )
    }else{
        return (
            <nav className="nav-wrapper blue darken-2">
                <div className="container">
                    <a className="brand-logo">Poliklinika za rehabilitaciju</a> 
                    <SingedOutLinks/>
                </div>
            </nav>
        )
    }
}

export default withRouter(Navbar)
