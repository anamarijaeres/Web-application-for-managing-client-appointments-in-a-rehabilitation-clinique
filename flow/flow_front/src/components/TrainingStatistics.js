import React, { Component } from 'react'
import axios from 'axios'
import { NavLink, withRouter } from 'react-router-dom';


 class TrainingStatistics extends Component {
        state= {
        statistics: []

        }

    componentDidMount(){
        axios.get('http://localhost:8080/getTrainingStatistics/' + this.props.matchLink.match.params.username )
        .then(response => {
            this.setState ({
                statistics: response.data
            });
         })

         console.log(this.state.statistics);
        }



    render() {
         const statistics = this.state.statistics;
                const List = statistics.map(s =>{

                    return(
                        <div className="post card" key={s.id}>
                            <div className="card-content">
                                <span className="card-title">{s.date}</span>
                                <p> Burned calories : {s.burnedCalories} kcal</p>
                                <NavLink to={"/editProfile" + s.id}>Show workouts </NavLink>
                            </div>
                        </div>

                    )
                });
     return(
         <div className="container" >
                {List}
         </div>
       )
    }
 }

 export default withRouter(TrainingStatistics);