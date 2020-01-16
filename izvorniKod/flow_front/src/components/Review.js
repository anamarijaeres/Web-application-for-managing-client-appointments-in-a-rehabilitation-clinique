import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class Review extends Component {
    state= {
        comment: '',
        score: '',

    }
    handleCommentChange=(e)=>{
        this.setState({comment: e.target.value})
    }
    handleScoreChange=(e)=>{
        this.setState({score: e.target.value})
    }

    handleSubmit = (e) =>  {
        const usernameDocCoach=localStorage.getItem("usernameDocCoach");
        const usernameClient=localStorage.getItem("userName")
        e.preventDefault();
        const data={
           comment: this.state.comment,
           score: this.state.score,
            usernameC:localStorage.getItem("userName")

        };
        axios.post('http://localhost:8080/review/'+ usernameDocCoach,data )
            .then(response => {
                    alert("Review successfully submitted!")
                    this.setState({
                        comment: '',
                        score: ''
                    });
                    this.props.history.push('/profile/'+usernameDocCoach);

            });
    }
    render()
    {
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit} className="white">
                    <h5 className="grey-text text-darken-3">Review</h5>
                    <div className="input-field">
                        <label htmlFor="username">Comment:</label>
                        <input type="text" id="comment" onChange={this.handleCommentChange} value={this.state.comment}/>
                    </div>
                    <div className="input-field">
                        <label >Score:</label>
                        <input type="number" id="score" onChange={this.handleScoreChange} value={this.state.score}/>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                </form>
            </div>
        )
    }
}
export default withRouter(Review);