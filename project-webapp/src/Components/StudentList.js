import React from "react";
import axios from 'axios';
import {Table, Card, Button} from "react-bootstrap";

class StudentList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            lecturerId: '',
            students: []
        };


    }

    valueChange  (event){
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    componentDidMount() {
        this.setState({lecturerId:localStorage.getItem('loggedUser')})
        console.log(localStorage.getItem('loggedUser'))
        axios.get("http://localhost:8080/students")
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
                    <Button variant="primary" href={"/lecturer/studentList/addStudent"}>Dodaj</Button>{' '}
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

export default StudentList;