import React from "react";
import axios from 'axios';
import {Card,Table} from 'react-bootstrap';
import { FiEdit  } from 'react-icons/fi';

class ProfilLecturer extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId : '',
            userType:'',
            users: {}
        }
    }

    componentDidMount() {

        this.setState({userId:localStorage.getItem('loggedUser')})
        axios({
            method:'get',
            url:'http://localhost:8080/student/'+localStorage.getItem('loggedUser'),
        }).then(response => response.data)
            .then((data) =>{
                this.setState({users: data});
            });
    }

    render() {
        return (

            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Body >
                    <Table border hover striped variant={"dark"} responsive>
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
                            <th>Imię</th>
                            <td>{this.state.users.imie} </td>
                            <td><a href={"/student/editName"}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <th>Nazwisko</th>
                            <td>{this.state.users.nazwisko} </td>
                            <td><a href={"/student/editSurname"}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <th>NrIndeksu</th>
                            <td>{this.state.users.nrIndeksu} </td>
                            <td><a href={"/student/editNrIndex"}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <th>Hasło</th>
                            <td>****</td>
                            <td> <a href={"/student/editPass"}><FiEdit   /></a></td>
                        </tr>

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ProfilLecturer;