import React from 'react'
import Woman from '../image.png'
import '../index.css'

const Home=()=>{
    return (
        <div className="home-container">
            <h4 className="center">Welcome page</h4>
            <div className="container">
            <img src={Woman} alt="Woman" className="responsive-img"/>
            </div>
        </div>
    )
}

export default Home;