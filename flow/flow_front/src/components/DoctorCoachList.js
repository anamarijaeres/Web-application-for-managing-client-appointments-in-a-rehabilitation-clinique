import React, { Component } from 'react'
import axios from 'axios'
// import { withRouter } from 'react-router-dom';

class DoctorCoachList extends Component{
    state={
        //role ti je u propsima
        //firstName i lastName isto
        doctorList:[],
        coachList:[]

    }

    componentDidMount() {
        axios.get('http://localhost:8080/doctorList')
            .then(response => {
                this.setState({
                    doctorList: response.data
                })
            })

        axios.get('http://localhost:8080/coachList').then(response => {
            this.setState({
                coachList: response.data
            })
        })

    }

    sendRequest(username, e, id) {
        e.preventDefault();
        const data = {
            firstname:this.props.firstName,
            lastname:this.props.lastName,
            username:this.props.username
        }

        axios.post('http://localhost:8080/sendRequest/'+username,data).then(response => {
            if(response.data.error_code === 'ERROR_CODE_0'){
                alert("You have successfully sent a request!")
            }else{
                alert("Your request has been denied.")
            }
        })
    }



    render() {
        if(this.props.role === 'Client') {
            const doctors = this.state.doctorList;
            const coaches=this.state.coachList;
            const doctorsList = doctors.map(doctor => {
                return (

                    <div className="post card" key={doctor.id}>
                        <div className="card-content">
                            <span className="card-title">{doctor.username}</span>
                            <p>{doctor.body}</p>
                            <button className="btn red lighten-1 z-depth-0"
                                    onClick={(e) => this.sendRequest(doctor.username, e, doctor.id)}>Posalji zahtjev
                            </button>
                        </div>
                    </div>

                )
            })
            const coachesList = coaches.map(coach=> {
                return (

                    <div className="post card" key={coach.id}>
                        <div className="card-content">
                            <span className="card-title">{coach.username}</span>
                            <p>{coach.body}</p>
                            <button className="btn red lighten-1 z-depth-0"
                                    onClick={(e) => this.sendRequest(coach.username, e, coach.id)}>Posalji zahtjev
                            </button>
                        </div>
                    </div>

                )
            })

            return (
                <div className="row">
                    <div className="col s6">
                        <div className="card">
                            <div className="card-content">
                                <span className="card-title center blue darken-2 white-text">Doktori</span>
                                <div className="container" >{doctorsList}</div>
                            </div>
                        </div>
                    </div>
                    <div className="col s6">
                        <div className="card">
                            <div className="card-content">
                                <span className="card-title center blue darken-2 white-text">Treneri</span>
                                <div className="container" >{coachesList}</div>
                            </div>
                        </div>
                    </div>
                </div>

                )


        } else {
           return (
               <div className="container">
                   OKEJ
               </div>
           )
        }
    }
}

export default DoctorCoachList;