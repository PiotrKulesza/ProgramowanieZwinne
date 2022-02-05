import React from "react";
import axios from 'axios';
import {Table, Card, Button} from "react-bootstrap";

class ProjectListLecturer extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            lecturerId: '',
            projects: []
        };


    }

    componentDidMount() {
        this.setState({lecturerId:localStorage.getItem('loggedUser')})
        console.log(localStorage.getItem('loggedUser'))
        axios.get("http://localhost:8080/lecturerProjects/"+localStorage.getItem('loggedUser'))
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
                    <Button variant="primary" href={"/lecturer/projects/addProject"}>Dodaj</Button>{' '}
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
                                    <td>{project.dataczasUtworzenia[0]+"-"+project.dataczasUtworzenia[1]+"-"
                                    +project.dataczasUtworzenia[2]}</td>
                                    {project.dataczasEdycji === null ?
                                        <td>Nie edytowane</td>
                                    :  <td>{project.dataczasEdycji[0]+"-"+project.dataczasEdycji[1]+"-"
                                        +project.dataczasEdycji[2]}</td>
                                    }
                                    {project.dataOddania === null ?
                                        <td>Nie oddane</td>
                                        :  <td>{project.dataOddania[0]+"-"+project.dataOddania[1]+"-"+project.dataOddania[2]}</td>
                                    }
                                    <td>
                                        <Button variant="primary" size="sm" href={"/lecturer/projects/stude" +
                                        "ntList?projectId="+project.projectId}>Studenci</Button>{' '}
                                        <Button variant="primary" size="sm" href={"/lecturer/projects/task" +
                                        "List?projectId="+project.projectId}>Zadania</Button>{' '}
                                        <Button variant="primary" size="sm" href={"/lecturer/projects/editProject" +
                                        "?projectId="+project.projectId}>Edytuj</Button>{' '}
                                        <Button variant="danger" size="sm" href={"/lecturer/projects/deleteProject" +
                                        "?projectId="+project.projectId}>Usuń</Button>
                                   </td>
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