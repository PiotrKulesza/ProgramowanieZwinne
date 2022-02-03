import React from "react";
import axios from 'axios';
import {Table, Card, Button} from "react-bootstrap";

class StudentsInProjectList extends React.Component{

    constructor(props) {
        const queryParams = new URLSearchParams(window.location.search);
        super(props);
        this.state = {
            projectId:queryParams.get('projectId'),
            lecturerId: '',
            students: []
        };


    }

    componentDidMount() {
        this.setState({lecturerId:localStorage.getItem('loggedUser')})
        console.log(localStorage.getItem('loggedUser'))
        axios.get("http://localhost:8080/getStudentsByProject?projectId="+this.state.projectId)
            .then(response => response.data)
            .then((data) =>{
                this.setState({students: data});
            });
        this.forceUpdate();
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-black"}>
                <Card.Header>
                    <Button variant="primary" href={"/lecturer/projects/studentList/addStudentToProject?projectId="+this.state.projectId}>Dodaj</Button>{' '}
                    <Button variant="primary" href={"/lecturer/projects"}>Powrót</Button>{' '}
                </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Imię</th>
                            <th>Nazwisko</th>
                            <th>Indeks</th>
                            <th>Email</th>

                        </tr>
                        </thead>
                        <tbody>
                        {this.state.students.length === 0 ?
                            <tr>
                                <td colSpan="5">Nie znaleziono studentów.</td>
                            </tr> :
                            this.state.students.map((student) => (
                                <tr>
                                    <td>{student.studentId}</td>
                                    <td>{student.imie}</td>
                                    <td>{student.nazwisko}</td>
                                    <td>{student.nrIndeksu}</td>
                                    <td>{student.login.email}</td>
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

export default StudentsInProjectList;