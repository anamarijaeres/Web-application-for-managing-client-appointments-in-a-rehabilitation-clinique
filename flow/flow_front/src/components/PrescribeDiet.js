import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class PrescribeDiet extends Component {
    state= {
        description: '',
        productList:[],
        value:'',
        valueCat:'',
        categoryList:[],
        energyLimit:'',
        fatLimit:'',
        saturatedFattyAcidsLimit:'',
        carbohydratesLimit:'',
        sugarsLimit:'',
        proteinLimit:'',
        saltLimit:''
    }

    componentDidMount(){
        axios.get('http://localhost:8080/productList')
            .then(response=>{
                this.setState({
                    productList:response.data
                })
            })
        axios.get('http://localhost:8080/categoryList')
            .then(response=>{
                this.setState({
                    categoryList:response.data
                })
            })
    }

    handleChange=(e)=>{
        this.setState({value:e.target.value})
    }

    handleDescriptionChange=(e)=>{
        this.setState({description: e.target.value})
    }

    handleCategoryChange=(e)=>{
        this.setState({valueCat: e.target.value})
    }

    handleEnergyChange=(e)=>{
        this.setState({energyLimit: e.target.value})
    }

    handleFatChange=(e)=>{
        this.setState({fatLimit: e.target.value})
    }

    handleAcidChange=(e)=>{
        this.setState({saturatedFattyAcidsLimit: e.target.value})
    }

    handleCarbohydratesChange=(e)=>{
        this.setState({carbohydratesLimit: e.target.value})
    }

    handleSugarsChange=(e)=>{
        this.setState({sugarsLimit: e.target.value})
    }

    handleProteinChange=(e)=>{
        this.setState({proteinLimit: e.target.value})
    }

    handleSaltChange=(e)=>{
        this.setState({saltLimit: e.target.value})
    }

    handleSubmit = (e) => {
        const usernameDoc=localStorage.getItem("userName")
        e.preventDefault();
        const data={
            description: this.state.description,
            username: this.props.matchLink.match.params.clientUsername
        };
        axios.post('http://localhost:8080/'+ usernameDoc+'/addDiet',data )
            .then(response => {
                if(response.data.error_code === 'ERROR_CODE_0' || response.data.error_code === 'ERROR_CODE_9' || response.data.error_code === 'ERROR_CODE_10'){
                    alert(response.data.message)
                    this.setState({
                        description:''
                    })
                }
            });
    }

    handleSubmit1 = (e) => {
        const usernameClient=this.props.matchLink.match.params.clientUsername
        e.preventDefault();
        const data={
            productName: this.state.value,
        };
        axios.post('http://localhost:8080/'+ usernameClient+'/addProductLimitation', this.state.value)
            .then(response => {
                if(response.data.error_code === 'ERROR_CODE_0' || response.data.error_code === 'ERROR_CODE_9'){
                    alert(response.data.message)
                    this.setState({
                        value:''
                    })
                }
            });
    }

    handleSubmit2 = (e) => {
        const usernameClient=this.props.matchLink.match.params.clientUsername
        e.preventDefault();
        const data={
            categoryName: this.state.valueCat,
        };
        axios.post('http://localhost:8080/'+ usernameClient+'/addCategoryLimitation', this.state.valueCat)
            .then(response => {
                if(response.data.error_code === 'ERROR_CODE_0' || response.data.error_code === 'ERROR_CODE_9'){
                    alert(response.data.message)
                    this.setState({
                        valueCat:''
                    })
                }
            });
    }

    handleSubmit3 = (e) => {
        const usernameDoc=localStorage.getItem("userName")
        e.preventDefault();
        const data= {
            energyLimit: this.state.energyLimit,
            fatLimit: this.state.fatLimit,
            saturatedFattyAcidsLimit: this.state.saturatedFattyAcidsLimit,
            carbohydratesLimit: this.state.carbohydratesLimit,
            sugarsLimit: this.state.sugarsLimit,
            proteinLimit: this.state.proteinLimit,
            saltLimit: this.state.saltLimit,
            username: this.props.matchLink.match.params.clientUsername        }
        axios.post('http://localhost:8080/addNutritionalValuesLimitation/'+usernameDoc, data)
            .then(response => {
                if(response.data.error_code === 'ERROR_CODE_0' || response.data.error_code === 'ERROR_CODE_9'){
                    alert(response.data.message)
                    this.setState({
                        energy:'',
                        fat:'',
                        saturatedFattyAcids:'',
                        carbohydrates:'',
                        sugars:'',
                        protein:'',
                        salt:''
                    })
                }
            });
    }

    render() {
        const products=this.state.productList.map(c=>{
            return(
                <option value={c.name}>{c.name}</option>
            )
        })
        const categories=this.state.categoryList.map(c=>{
            return(
                <option value={c.name}>{c.name}</option>
            )
        })

        return(
            <div className="container">

                <form onSubmit={this.handleSubmit} className="white">
                    <h4 className="grey-text text-darken-3">Diet</h4>
                    <div className="input-field">
                        <label htmlFor="username">Description:</label>
                        <input type="text" id="description" onChange={this.handleDescriptionChange} value={this.state.description}/>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                    <div className="container">
                        <label> .</label>
                    </div>
                </form>

                <form onSubmit={this.handleSubmit1} className="white">
                    <h4 className="grey-text text-darken-3">Product limitation</h4>
                    <div className= "container">
                        <select className="browser-default" value={this.state.value} onChange={this.handleChange}>
                            <option value="" disabled selected>Choose a product</option>
                            {products}
                        </select>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                    <div className="container">
                        <label> .</label>
                    </div>
                </form>

                <form onSubmit={this.handleSubmit2} className="white">
                    <h4 className="grey-text text-darken-3">Category limitation</h4>
                    <div className= "container">
                        <select className="browser-default" value={this.state.valueCat} onChange={this.handleCategoryChange}>
                            <option value="" disabled selected>Choose a category</option>
                            {categories}
                        </select>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                    <div className="container">
                        <label> .</label>
                    </div>
                </form>

                <form onSubmit={this.handleSubmit3}>
                    <h4 className="grey-text text-darken-3">Nutritional values limitation</h4>
                    <div className="col s6 offset-s3">
                        <div></div>
                        <div className="input-field">
                            <label htmlFor="energy">Energy</label>
                            <input type="number" step="0.01" id="energy" onChange={this.handleEnergyChange} value={this.state.energy}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="fat">Fat</label>
                            <input type="number" step="0.01" id="fat" onChange={this.handleFatChange} value={this.state.fat}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="saturatedFattyAcids">Saturated fatty acids</label>
                            <input type="number" step="0.01" id="saturatedFattyAcids" onChange={this.handleAcidChange} value={this.state.saturatedFattyAcids}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="carbohydrates">Carbohydrates</label>
                            <input type="number" step="0.01" id="carbohydrates" onChange={this.handleCarbohydratesChange} value={this.state.carbohydrates}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="sugars">Sugars</label>
                            <input type="number" step="0.01" id="sugars" onChange={this.handleSugarsChange} value={this.state.sugars}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="protein">Protein</label>
                            <input type="number" step="0.01" id="protein" onChange={this.handleProteinChange} value={this.state.protein}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="salt">Salt</label>
                            <input type="number" step="0.01" id="salt" onChange={this.handleSaltChange} value={this.state.salt}/>
                        </div>
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                </form>
            </div>
        )

    }
}
export default withRouter(PrescribeDiet);