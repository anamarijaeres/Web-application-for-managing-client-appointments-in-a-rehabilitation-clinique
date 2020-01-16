import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import axios from 'axios'
import EditExerciseForm from './EditExerciseForm'


class DeleteExercise extends Component {

    state = {
        list: [],
        name: ''
    }

    componentDidMount(){

        axios.get('http://localhost:8080/getExercises')
        .then(response=>{
            this.setState({
                    list: response.data
            });
        })
    }

    handleNameChange=(e)=> {
             this.setState({
                name: e.target.value
             });


     }

     handleSubmit=(e)=> {
             e.preventDefault();
             axios.delete('http://localhost:8080/'+ this.state.name)
             .then(response => {
                alert("Successfully removed.")
             })
         }

    render() {

    return (
       <div className="container center">
       <form onSubmit={this.handleSubmit}>
        <div className="input-field col s12">
            <select className="browser-default" onChange={this.handleNameChange}>
            <option value="" disabled selected>Select exercise:</option>
              {this.state.list.map((workout)=>{
                       return(
                           <option value={workout}>{workout}</option>
                       )
                   })}
            </select>
         </div>
            <button className="btn red lighten-1 z-depth-0">Delete</button>
         </form>
        </div>

    )
    }
}

export default withRouter(DeleteExercise)