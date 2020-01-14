import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';


class AddProduct extends Component {
    state={
        name:'', 
        energy:'',
        fat:'',
        saturatedFattyAcids:'',
        carbohydrates:'',
        sugars:'',
        protein:'',
        salt:'',
        mass:'', 
        categoryList:[],
        allergens:[{name:''}],
        value:'',
        image: null,
        barcode:null
    }

    componentDidMount(){
        axios.get('http://localhost:8080/categoryList')
        .then(response=>{
            this.setState({
                categoryList:response.data
            })
        })
    }

    handleProductSubmit=(e)=>{
        e.preventDefault();
        const data={
            name:this.state.name,
            energy:this.state.energy,
            fat:this.state.fat,
            saturatedFattyAcids:this.state.saturatedFattyAcids,
            carbohydrates:this.state.carbohydrates,
            sugars:this.state.sugars,
            protein:this.state.protein,
            salt:this.state.salt,
            mass:this.state.mass, 
            category:this.state.value
        };

        const data2=this.state.allergens.map(alrg=>{
            return alrg.name
        })


        let formData1=new FormData();
        formData1.append('imageFile', this.state.image);


        let formData2=new FormData();
        formData2.append('barcodeImageFile', this.state.barcode);

        return axios.post('http://localhost:8080/addProduct', data)
            .then(response=>{
                if(response.data.error_code === 'ERROR_CODE_0'){
                    alert(response.data.message)
        
                    axios.post('http://localhost:8080/addAllergens/'+this.state.name, data2);

                    axios.post('http://localhost:8080/saveProductImage/'+this.state.name, formData1, {
                                headers:{
                                    'content-type': 'multipart/form-data' 
                                }
                        });
                    
                    axios.post('http://localhost:8080/saveProductBarcodeImage/'+this.state.name, formData2, {
                        headers:{
                            'content-type': 'multipart/form-data' 
                        }
                    });

                    this.setState({
                        name:'',
                        energy:'',
                        fat:'',
                        saturatedFattyAcids:'',
                        carbohydrates:'',
                        sugars:'',
                        protein:'',
                        salt:'',
                        mass:'', 
                        categoryList:[],
                        value:'',
                        allergens:[{name:''}],
                        image: null,
                        barcode:null
                    });



                    this.props.history.push('/'+localStorage.getItem('userName'))
                }else{
                    this.setState({
                        name:'',
                        energy:'',
                        fat:'',
                        saturatedFattyAcids:'',
                        carbohydrates:'',
                        sugars:'',
                        protein:'',
                        salt:'',
                        mass:'', 
                        categoryList:[],
                        value:'',
                        allergens:[{name:''}],
                        image: null,
                        barcode:null
                    });
                    alert(response.data.message)
                }
            });
    }


    handleNameChange=(e)=>{
        this.setState({name: e.target.value})
    }

    handleEnergyChange=(e)=>{
        this.setState({energy: e.target.value})
    }

    handleFatChange=(e)=>{
        this.setState({fat: e.target.value})
    }

    handleSaturatedFattyAcidsChange=(e)=>{
        this.setState({saturatedFattyAcids: e.target.value})
    }

    handleCarbohydratesChange=(e)=>{
        this.setState({carbohydrates: e.target.value})
    }

    handleSugarsChange=(e)=>{
        this.setState({sugars: e.target.value})
    }

    handleProteinChange=(e)=>{
        this.setState({protein: e.target.value})
    }

    handleSaltChange=(e)=>{
        this.setState({salt: e.target.value})
    }

    handleChange=(e)=>{
        this.setState({
            value:e.target.value
        })
    }

    handleAllergenNameChange=idx=>(e)=>{
        const newAllergens=this.state.allergens.map((alrg, sidx)=>{
            if(idx!==sidx){
                return alrg;
            }else{
                return { ...alrg, name: e.target.value };
            }
        })

        this.setState({
            allergens:newAllergens
        })
    }

    handleAllergenRemove=idx=>(e)=>{    
        this.setState({
            allergens:this.state.allergens.filter((alrg, sidx)=> idx!==sidx)
        })
    }

    handleAllergenAdd=(e)=>{
        this.setState({
            allergens:this.state.allergens.concat([{ name: "" }])
        })
    }

    handleImageChange=(e)=>{
        this.setState({image:e.target.files[0]})
    }

    handleBarcodeChange=(e)=>{
        this.setState({barcode:e.target.files[0]})
    }

    render(){
        const categories=this.state.categoryList.map(c=>{
            return(
                <option value={c.name}>{c.name}</option> 
            )
        })

        const allergensList=this.state.allergens.map((alrg, idx)=>{
            return(
                <div className="allergens">
                    <input type="text" value={alrg.name} onChange={this.handleAllergenNameChange(idx)}/>
                    <button type="button" onClick={this.handleAllergenRemove(idx)} className="small">x</button>
                </div>
            )
        })

        return(
            <div className= "container">
                <div className="row">
                    <form onSubmit={this.handleProductSubmit}>
                    <div className="col s6 offset-s3">
                        <div></div>
                        <div className="input-field">
                            <label htmlFor="name">Naziv</label>
                            <input type="text" id="name" onChange={this.handleNameChange} value={this.state.name}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="energy">Energija</label>
                            <input type="number" step="0.01" id="energy" onChange={this.handleEnergyChange} value={this.state.energy}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="fat">Masnoće</label>
                            <input type="number" step="0.01" id="fat" onChange={this.handleFatChange} value={this.state.fat}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="saturatedFattyAcids">Zasićene masne kiseline</label>
                            <input type="number" step="0.01" id="saturatedFattyAcids" onChange={this.handleSaturatedFattyAcidsChange} value={this.state.saturatedFattyAcids}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="carbohydrates">Ugljikohidrati</label>
                            <input type="number" step="0.01" id="carbohydrates" onChange={this.handleCarbohydratesChange} value={this.state.carbohydrates}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="sugars">Šećeri</label>
                            <input type="number" step="0.01" id="sugars" onChange={this.handleSugarsChange} value={this.state.sugars}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="protein">Proteini</label>
                            <input type="number" step="0.01" id="protein" onChange={this.handleProteinChange} value={this.state.protein}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="salt">Sol</label>
                            <input type="number" step="0.01" id="salt" onChange={this.handleSaltChange} value={this.state.salt}/>
                        </div>
                        <div className= "container">
                            <select className="browser-default" value={this.state.value} onChange={this.handleChange}>
                                <option value="" disabled selected>Please choose category of product:</option>
                                {categories}
                            </select>
                        </div>
                        <div className= "container">
                            <p>Alergeni:</p>
                            {allergensList}
                            <button type="button" onClick={this.handleAllergenAdd} className="small">Dodaj alergen</button>
                        </div>
                        <div className="input-field"> 
                        <label htmlFor="image">Slika</label>
                            <input type="file" onChange={this.handleImageChange}/>
                        </div>
                        <div className="input-field"> 
                        <label htmlFor="image">Barkod</label>
                            <input type="file" onChange={this.handleBarcodeChange}/>
                        </div>
                        <button className="btn red lighten-1 z-depth-0">Dodaj proizvod</button>
                    </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default withRouter(AddProduct);
