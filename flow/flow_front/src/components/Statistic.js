import React, { Component } from 'react'
import axios from 'axios'
import {NavLink, withRouter} from 'react-router-dom';

class Statistic extends Component{
    state={
        productList: []
    }

    componentDidMount() {
        axios.get('http://localhost:8080/statistic/' + this.props.matchLink.match.params.username)
            .then(response => {
                this.setState({
                    productList: response.data
                })
            })
    }

    render(){
        const statistics = this.state.productList;
        const stat = statistics.map(statistic=> {
            return (

                <div className="post card" key={statistic.id}>

                    <div className="card-content">
                        <span className="card-title">Day:{statistic.date}</span>
                        <p>{statistic.body}</p>

                    </div>
                </div>

            )
        })
            return(
                <div>
                <div className="col s12 m4">
                     <div className="card blue darken-2">
                          <div className="card-content white-text">
                               <span className="card-title">Statistics:</span>
                                    <div className="containter black-text">
                                            {stat}
                                    </div>
                          </div>

                     </div>
                </div>
                <div className="center card-action">
                     <NavLink to={"/"+ localStorage.getItem("userName")}>Back</NavLink>
                 </div>
                </div>
            )
    }


}
export default withRouter(Statistic);