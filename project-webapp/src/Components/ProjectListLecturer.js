import React from "react";
import axios from 'axios';
import {Table, Card} from "react-bootstrap";

class ProjectListLecturer extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            lecturerId: '',
            text: '',
            projects: []
        };
        this.valueChange = this.valueChange.bind(this)
        this.submitSearch = this.submitSearch.bind(this)
    }

    valueChange  (event){
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    submitSearch (event) {

        axios({
            method:'get',
            url:'http://localhost:8080/getUserByText?text='+this.state.text,
        }).then(response => response.data)
            .then((data) =>{
                this.setState({users: data});
            });
        this.forceUpdate();
        event.preventDefault();
    }

    componentDidMount() {
        this.setState({lecturerId:localStorage.getItem('loggedUser')})
        console.log(localStorage.getItem('loggedUser'))
        axios.get("http://localhost:8080/lecturerProjects/"+this.state.lecturerId)
            .then(response => response.data)
            .then((data) =>{
                this.setState({projects: data});
            });
        this.forceUpdate();
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-black"}>
                <Card.Header>
                </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nazwa</th>
                            <th>Opis</th>
                            <th>Utworzony</th>
                            <th>Edytowany</th>
                            <th>Oddany</th>
                            <th>Działania</th>

                        </tr>
                        </thead>
                        <tbody>
                        {this.state.projects.length === 0 ?
                            <tr>
                                <td colSpan="7">Nie znaleziono projektów.</td>
                            </tr> :
                            this.state.projects.map((project) => (
                                <tr>
                                    <td>{project.projectId}</td>
                                    <td>{project.nazwa}</td>
                                    <td>{project.opis}</td>
                                    <td>{project.dataczasUtworzenia}</td>
                                    <td>{project.dataczasEdycji}</td>
                                    <td>{project.dataOddania}</td>
                                    <td>Coś tam</td>
                                </tr>

                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ProjectListLecturer;