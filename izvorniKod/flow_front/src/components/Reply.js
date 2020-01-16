import React, { Component } from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class Reply extends Component {
    state= {
        reply: ''
    }

    handleReplyChange=(e)=>{
        this.setState({reply: e.target.value})
    }


    handleSubmit = (e) =>  {
        const username=localStorage.getItem("userName")
        e.preventDefault();
        const data={
            reply: this.state.reply,
            id:this.props.matchLink.match.params.id,
            usernameDocCoach:this.props.matchLink.match.params.us
        };
        axios.post('http://localhost:8080/reply/'+ this.props.matchLink.match.params.username,data )
            .then(response => {
                alert("Reply successful!")
                this.setState({
                    reply: '',
                });
                this.props.history.push('/'+username);

            });
    }
    render()
    {
        console.log(this.props.matchLink.match.params.id);
        console.log(typeof (this.props.matchLink.match.params.id));
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit} className="white">
                    <h5 className="grey-text text-darken-3">Reply</h5>
                    <div className="input-field">
                        <label htmlFor="username">Comment:</label>
                        <input type="text" id="reply" onChange={this.handleReplyChange} value={this.state.reply}/>
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Submit</button>
                    </div>
                </form>
            </div>
        )
    }
}
export default withRouter(Reply);