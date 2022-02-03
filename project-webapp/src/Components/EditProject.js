import React from "react";
import axios from 'axios';
import {Button, Card, Table} from 'react-bootstrap';
import { FiEdit  } from 'react-icons/fi';

class EditProject extends React.Component{
    constructor(props) {
        const queryParams = new URLSearchParams(window.location.search);
        super(props);
        this.state = {
            projectId:queryParams.get('projectId'),
            userId : '',
            userType:'',
            project: {}
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
            url:'http://localhost:8080/getProjectsByProjectId?projectId='+this.state.projectId,
        }).then(response => response.data)
            .then((data) =>{
                this.setState({project: data});
            });
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <Button size="sm" href={"/"+this.state.userType+"/projects"} >
                        Powrót
                    </Button>{" "}
                    <Button size="sm" href={"/"+this.state.userType+"/projects/editProject/endProject?projectId="+this.state.projectId} >
                        Zakończ
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
                            <td >{this.state.project.nazwa} </td>
                            <td><a href={"/"+this.state.userType+"/projects/editProject/editProjectName?&projectId="+this.state.projectId}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <td>Opis</td>
                            <td>{this.state.project.opis} </td>
                            <td><a href={"/"+this.state.userType+"/projects/editProject/editProjectDescription?&projectId="+this.state.projectId}><FiEdit   /></a></td>
                        </tr>

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default EditProject;