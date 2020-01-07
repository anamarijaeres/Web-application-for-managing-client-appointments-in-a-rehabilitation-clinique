import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';
import FormData from 'form-data'

class AddProductCategory extends Component {

    state={
        submitCategory:{
            categoryName:''
        }
    }

    handleCategoryChange=(e)=>{
        this.setState({submitCategory:{...this.state.submitCategory, categoryName:e.target.value}})
    }

    handleCategorySubmit=(e)=>{
        e.preventDefault();
        const data={
            categoryName: this.state.submitCategory.categoryName
        };

        return axios.post('http://localhost:8080/addCategory', data)
        .then(response=>{
            alert("You've successfully submitted category")
        });
    }

    render(){
        return(
            <div className= "container">
                <div className="row">
                    <form onSubmit={this.handleCategorySubmit}>
                    <div className="col s6 offset-s3">
                        <div></div>
                        <div className="input-field">
                            <label htmlFor="categoryName">Naziv</label>
                            <input type="text" id="categoryName" onChange={this.handleCategoryChange} value={this.state.submitCategory.categoryName}/>
                        </div>
                        <button className="btn red lighten-1 z-depth-0">Dodaj kategoriju</button>
                    </div>
                    </form>
                </div>
            </div>
        )


    }
}

export default AddProductCategory