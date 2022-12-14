import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class DeleteProduct extends Component {
    state = {
        productList:[],
        value: ''
    }

    componentDidMount(){
        axios.get('http://localhost:8080/productList')
            .then(response=>{
                this.setState({
                    productList:response.data
                })
            })
    }

    handleChange=(e)=>{
        this.setState({
            value:e.target.value
        })
    };

    handleProductSubmit=(e)=>{
        e.preventDefault();
        const data={
            name: this.state.value
        };

        return axios.post('http://localhost:8080/deleteProduct', data)
            .then(response=>{
                this.props.history.push('/ADMIN');
            });

    }


    render() {
        const products = this.state.productList.map(c => {
            return (
                <option value={c.name}>{c.name}</option>
            )
        })
        return (
            <div className="container center">
                    <form onSubmit={this.handleProductSubmit}>
                        <div className="input-field col s12">

                                <select className="browser-default" value={this.state.value}
                                        onChange={this.handleChange}>
                                    <option value="" disabled selected>Please choose product:</option>
                                    {products}
                                </select>
                               </div>
                            <button className="btn red lighten-1 z-depth-0 ">Delete</button>

                    </form>

            </div>


        )
    }
}
export default withRouter(DeleteProduct);

