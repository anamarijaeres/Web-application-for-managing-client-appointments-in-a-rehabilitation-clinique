import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class AddProductCategory extends Component {

    state={
        categoryName:''    
    }

    handleCategoryChange=(e)=>{
        this.setState({
            categoryName:e.target.value
        })
    }

    handleCategorySubmit=(e)=>{
        e.preventDefault();
        const data={
            name: this.state.categoryName
        };

        return axios.post('http://localhost:8080/addProductCategory', data)
        .then(response=>{
            if(response.data.error_code === 'ERROR_CODE_0'){
                alert(response.data.message)
                this.setState({
                    categoryName:''
                })
                this.props.history.push('/'+localStorage.getItem('userName'))
            }else if(response.data.error_code === 'ERROR_CODE_9' || response.data.error_code === 'ERROR_CODE_10'){
                alert(response.data.message)
                this.setState({
                    categoryName:''
                })
            }
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
                            <input type="text" id="categoryName" onChange={this.handleCategoryChange} value={this.state.categoryName}/>
                        </div>
                        <button className="btn red lighten-1 z-depth-0">Dodaj kategoriju</button>
                    </div>
                    </form>
                </div>
            </div>
        )


    }
}

export default withRouter(AddProductCategory);
