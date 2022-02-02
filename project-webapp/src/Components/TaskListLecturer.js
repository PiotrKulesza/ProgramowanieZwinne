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
            tasks: []
        };


    }

    componentDidMount() {
        this.setState({lecturerId:localStorage.getItem('loggedUser')})
        console.log(localStorage.getItem('loggedUser'))
        axios.get("http://localhost:8080/getTaskByProject?projectId="+this.state.projectId)
            .then(response => response.data)
            .then((data) =>{
                this.setState({tasks: data});
            });
        this.forceUpdate();
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-black"}>
                <Card.Header>
                    <Button variant="primary" href={"/lecturer/projects/taskLis/addTask?projectId="+this.state.projectId}>Dodaj</Button>{' '}
                </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nazwa</th>
                            <th>Opis</th>
                            <th>Kanban</th>
                            <th>Działania</th>

                        </tr>
                        </thead>
                        <tbody>
                        {this.state.tasks.length === 0 ?
                            <tr>
                                <td colSpan="5">Nie znaleziono studentów.</td>
                            </tr> :
                            this.state.tasks.map((task) => (
                                <tr>
                                    <td>{task.taskId}</td>
                                    <td>{task.nazwa}</td>
                                    <td>{task.opis}</td>
                                    <td>{task.kanban}</td>
                                    <td> <Button size="sm" variant="primary" >Edycja</Button>{' '}</td>
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