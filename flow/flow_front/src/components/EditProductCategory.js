import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';
import SignupForm from "./SignupForm";


class EditProductCategory extends Component {
    state={
        name:'',
        categoryList:[],
        value:''
    }

    componentDidMount(){
        axios.get('http://localhost:8080/categoryList')
            .then(response=>{
                this.setState({
                    categoryList:response.data
                })
            })
    }

    handleCategorySubmit=(e)=>{
        const name = this.state.value;
        e.preventDefault();
        const data={
            name: this.state.categoryName
        };

        return axios.put('http://localhost:8080/editCategory/'+ name, data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0'){
                    alert(response.data.message)
                    this.setState({
                        categoryName:''
                    })
                    this.props.history.push('/ADMIN');
                }else if(response.data.error_code === 'ERROR_CODE_9' || response.data.error_code === 'ERROR_CODE_10'){
                    alert(response.data.message)
                    this.setState({
                        categoryName:''
                    })
                }
            });
    }

    handleNameChange=(e)=> {
        this.setState({
            categoryName:e.target.value

        })
    }

    handleChange=(e)=>{
        this.setState({
            value:e.target.value
        })
    }

    render(){
        const categories=this.state.categoryList.map(c=>{
            return(
                <option value={c.name}>{c.name}</option>
            )
        })
        return(
            <div className= "container">
                <div className="row">
                    <form onSubmit={this.handleCategorySubmit}>
                        <div className="col s6 offset-s3">
                            <div></div>
                            <div className="input-field">
                                <label htmlFor="categoryName">Naziv</label>
                                <input type="text" id="categoryName" onChange={this.handleNameChange} value={this.state.categoryName}/>
                            </div>
                            <div className= "container">
                                <select className="browser-default" value={this.state.value} onChange={this.handleChange}>
                                    <option value="" disabled selected>Please choose category of product:</option>
                                    {categories}
                                </select>
                            </div>
                            <button className="btn red lighten-1 z-depth-0">Dodaj kategoriju</button>
                        </div>
                    </form>
                </div>
            </div>


        )

    }
}

export default withRouter(EditProductCategory);
