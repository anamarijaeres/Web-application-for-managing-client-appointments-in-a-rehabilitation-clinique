import React, { Component } from 'react'
import axios from 'axios'
import { NavLink, withRouter } from 'react-router-dom';

class LoadExercises extends Component{
    state = {
        exercises: [],
    }

    componentDidMount(){
    console.log(this.state.id)
            axios.get('http://localhost:8080/loadExercisesFromStatistics/'+this.props.matchLink.match.params.id)
            .then(response => {
                this.setState ({
                    exercises: response.data
                });

             })

            }

      render() {

      const exercises = this.state.exercises;
              const trainingList = exercises.map(exercise =>{
                  return(
                      <tr>
                          <td>{exercise.exerciseName}</td>
                          <td>{exercise.mode}</td>
                          <td>{exercise.duration}</td>
                      </tr>
                  )
              });
        return (
        <div className="container">
        <table>
                <thead>
                  <tr>
                      <th>Name :</th>
                      <th>Intensity :</th>
                      <th>Duration : </th>
                  </tr>
                </thead>

                <tbody>
                    {trainingList}
                </tbody>
          </table>
        </div>

        )
      }
}

export default withRouter(LoadExercises)