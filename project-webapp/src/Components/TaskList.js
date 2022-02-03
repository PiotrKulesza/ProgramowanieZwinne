import React from "react";
import axios from 'axios';
import {Table, Card, Button} from "react-bootstrap";
import CanvasJSReact from "../canvasjs.react";


class StudentsInProjectList extends React.Component{

    constructor(props) {
        const queryParams = new URLSearchParams(window.location.search);
        super(props);
        this.state = {
            projectId:queryParams.get('projectId'),
            lecturerId: '',
            tasks: [],
            newTasks:0,
            todoTasks:0,
            doingTasks:0,
            completedTasks:0,
            userType:''
        };


    }

    componentDidMount() {
        this.setState({userId:localStorage.getItem('loggedUser')})
        if ('STUDENT' === localStorage.getItem('typeOfUser')) {
            this.setState({userType:"student"})

        }else{
            this.setState({userType:"lecturer"})

        }
        axios.get("http://localhost:8080/getTaskByProject?projectId="+this.state.projectId)
            .then(response => response.data)
            .then((data) =>{
                this.setState({tasks: data});
                this.setState( {newTasks:this.state.tasks.filter(function(element){
                    return element.kanban == 'NEW';
                }).length});
                this.setState( {doingTasks:this.state.tasks.filter(function(element){
                        return element.kanban == 'DOING';
                    }).length});
                this.setState( {todoTasks:this.state.tasks.filter(function(element){
                        return element.kanban == 'TODO';
                    }).length});
                this.setState( {completedTasks:this.state.tasks.filter(function(element){
                        return element.kanban == 'COMPLETED';
                    }).length});

            });
        this.forceUpdate();
    }

    render() {
        const options = {
            animationEnabled: true,
            exportEnabled: true,
            theme: "dark1", //"light1", "dark1", "dark2"
            title:{
                text: "Wykres kanban"
            },
            axisY: {
                includeZero: true
            },
            data: [{
                type: "column", //change type to bar, line, area, pie, etc
                //indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "white",
                indexLabelPlacement: "outside",
                dataPoints: [
                    { "label":"NEW", y: this.state.newTasks, indexLabel: "NEW" },
                    { "label":"DOING", y: this.state.doingTasks, indexLabel: "DOING" },
                    { "label":"TODO", y: this.state.todoTasks, indexLabel: "TODO" },
                    { "label":"COMPLETED", y: this.state.completedTasks, indexLabel: "COMPLETED" }

                ]
            }]
        }
        return (
            <Card className={"border border-dark bg-dark text-black"}>
                <Card.Header>
                    <Button variant="primary" href={"/"+this.state.userType+"/projects/taskList/addTask?projectId="+this.state.projectId}>Dodaj</Button>{' '}
                    <Button variant="primary" href={"/"+this.state.userType+"/projects"}>Powrót</Button>{' '}
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
                                    <td> <Button size="sm" variant="primary" href={"/"+this.state.userType+"/projects" +
                                    "/taskList/editTask?projectId="+this.state.projectId+"&taskId="+task.taskId}>Edycja</Button>{' '}</td>
                                </tr>

                            ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
                <Card.Footer>
                    <CanvasJSReact.CanvasJSChart options = {options}></CanvasJSReact.CanvasJSChart>
                </Card.Footer>
            </Card>
        );
    }
}

export default StudentsInProjectList;