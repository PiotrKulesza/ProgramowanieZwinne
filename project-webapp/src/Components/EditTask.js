import React from "react";
import axios from 'axios';
import {Button, Card, Table} from 'react-bootstrap';
import { FiEdit  } from 'react-icons/fi';

class EditTask extends React.Component{
    constructor(props) {
        const queryParams = new URLSearchParams(window.location.search);
        super(props);
        this.state = {
            projectId:queryParams.get('projectId'),
            taskId:queryParams.get('taskId'),
            userId : '',
            userType:'',
            task: {}
        }
    }

    componentDidMount() {

        this.setState({userId:localStorage.getItem('loggedUser')})
        if ('STUDENT' === localStorage.getItem('typeOfUser')) {
            this.setState({userType:"student"})

        }else{
            this.setState({userType:"lecturer"})

        }
        axios({
            method:'get',
            url:'http://localhost:8080/getTaskById?taskId='+this.state.taskId,
        }).then(response => response.data)
            .then((data) =>{
                this.setState({task: data});
            });
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <Button size="sm" href={"/"+this.state.userType+"/projects/taskList?projectId="+this.state.projectId} >
                        Powrót
                    </Button>
                </Card.Header>
                <Card.Body>
                    <Table border hover striped variant={"dark"}>
                        <thead>
                        <tr>
                            <th>
                                Opis
                            </th>
                            <th>
                                Wartość
                            </th>
                            <th>
                                Działania
                            </th>
                        </tr>
                        </thead>
                        <tbody>


                        <tr>
                            <td>Nazwa</td>
                            <td >{this.state.task.nazwa} </td>
                            <td><a href={"/"+this.state.userType+"/projects/taskList/editTask/editTaskName?taskId="+this.state.taskId
                            +"&projectId="+this.state.projectId}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <td>Opis</td>
                            <td>{this.state.task.opis} </td>
                            <td><a href={"/"+this.state.userType+"/projects/taskList/editTask/editTaskDescription?taskId="+this.state.taskId
                            +"&projectId="+this.state.projectId}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <td>Etap</td>
                            <td>{this.state.task.kanban} </td>
                            <td> {this.state.task.kanban !== "NEW" ?
                                <Button size="sm" href={"/"+this.state.userType+"/projects/taskList/editTask/kanbanNEW?taskId="+this.state.taskId
                                +"&projectId="+this.state.projectId} >
                                    NEW
                                </Button> : <b></b>

                            }
                                {"  "}
                                {this.state.task.kanban !== "TODO" ?
                                    <Button size="sm" href={"/"+this.state.userType+"/projects/taskList/editTask/kanbanTODO?taskId="+this.state.taskId
                                    +"&projectId="+this.state.projectId} >
                                        TODO
                                    </Button> : <b></b>

                                }
                                {"  "}
                                {this.state.task.kanban !== "DOING" ?
                                    <Button size="sm" href={"/"+this.state.userType+"/projects/taskList/editTask/kanbanDOING?taskId="+this.state.taskId
                                    +"&projectId="+this.state.projectId} >
                                        DOING
                                    </Button> : <b></b>

                                }
                                {"  "}
                                {this.state.task.kanban !== "COMPLETED" ?
                                    <Button size="sm" href={"/"+this.state.userType+"/projects/taskList/editTask/kanbanCOMPLETED?taskId="+this.state.taskId
                                    +"&projectId="+this.state.projectId} >
                                        COMPLETED
                                    </Button>: <b></b>

                                }
                                {"  "}


                            </td>
                        </tr>

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default EditTask;