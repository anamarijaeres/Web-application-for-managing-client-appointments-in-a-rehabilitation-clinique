import React, { Component } from 'react'


class AddProductCategory extends Component {

    render(){
        return(
            <div className= "container">
                <div className="row">
                <div className="col s6 offset-s3">
                    <div></div>
                    <div className="input-field">
                        <label htmlFor="lastName">Naziv</label>
                        <input type="text" id="lastName"/>
                    </div>
                        <button className="btn red lighten-1 z-depth-0">Dodaj kategoriju</button>
                </div>
                </div>
            </div>
        )


    }
}

export default AddProductCategory