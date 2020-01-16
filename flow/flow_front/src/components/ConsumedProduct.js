import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class ConsumedProduct extends Component {

    state = {
        name:'',
        list: [],
        mass: '',
        barcode:null
    }

    componentDidMount() {
        axios.get('http://localhost:8080/getProducts')
            .then(response=>{
                this.setState({
                    list: response.data
                });
            })
    }
    handleNameChange=(e)=> {
        this.setState({
            name: e.target.value
        })
    }
    handleMassChange=(e)=>{
        this.setState({mass: e.target.value})
    }

    handleBarcodeChange=(e)=>{
        this.setState({barcode:e.target.files[0]})
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const data= {
            username: this.props.matchLink.match.params.username,
            productName: this.state.name,
            mass:this.state.mass
        };
        if(this.state.barcode===null){
        return axios.post('http://localhost:8080/addConsumedProduct', data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0') {
                    alert("Product fits in your diet!");
                    this.setState({
                        name: '',
                        list: [],
                        mass: ''
                    })
                    this.props.history.push('/' + localStorage.getItem('userName'));
                }else{
                    alert("Product doesn't fit in your diet!");
                    this.setState({
                        name: '',
                        list: [],
                        mass: ''
                    })
                    this.props.history.push('/' + localStorage.getItem('userName'));
                }
            });}
            else{
                let form=new FormData();
                form.append('imageFile', this.state.barcode);
                return axios.post('http://localhost:8080/findProductbyBarcode', form)
                    .then(response=>{
                        if(response.data.error_code==='ERROR_CODE_0'){
                            data.productName=response.data.username;
                            alert(response.data.username);
                            axios.post('http://localhost:8080/addConsumedProduct', data)
                                .then(response=>{
                                    if(response.data.error_code === 'ERROR_CODE_0') {
                                        alert("Product fits in your diet!");
                                        this.setState({
                                            name: '',
                                            list: [],
                                            mass: ''
                                        })
                                        this.props.history.push('/' + localStorage.getItem('userName'));
                                    }else{
                                        alert("Product doesn't fit in your diet!");
                                        this.setState({
                                            name: '',
                                            list: [],
                                            mass: ''
                                        })
                                        this.props.history.push('/' + localStorage.getItem('userName'));
                                    }
                                });
                        }else if(response.data.error_code==='ERROR_CODE_11'){
                            alert(response.data.message);
                            this.setState({
                                name: '',
                                list: [],
                                mass: ''
                            })
                        }
                        
                    })
            }

    }
    render() {
        const products = this.state.list;
        const productList= products.map((workout)=>{
            return(
                <option value={workout} >{workout}</option>
            )
        });
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit} className="white">
                    <div className="input-field col s12">
                        <select className="browser-default" onChange={this.handleNameChange}>
                            <option value="" disabled selected>Select product:</option>
                            {productList}
                        </select>
                    </div>
                    <label></label>
                    <label>Or add product barcode</label>
                    <div className="input-field"> 

                            <input type="file" onChange={this.handleBarcodeChange}/>
                        </div>
                    <div className="input-field">
                        <label htmlFor="mass">Masa</label>
                        <input type="number" step="0.01" id="mass" onChange={this.handleMassChange} value={this.state.mass}/>
                    </div>
                        <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>

                </form>
            </div>
        )
    }

}

export default withRouter(ConsumedProduct);