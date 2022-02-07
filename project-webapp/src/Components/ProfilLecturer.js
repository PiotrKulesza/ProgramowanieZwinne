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
            url:'http://localhost:8080/lecturer/'+localStorage.getItem('loggedUser'),
        }).then(response => response.data)
            .then((data) =>{
                this.setState({users: data});
            });
    }

    render() {
        return (

            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <h4>Profil Wykładowcy</h4>
                </Card.Header>
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
                            <td><a href={"/lecturer/editName"}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <th>Nazwisko</th>
                            <td>{this.state.users.nazwisko} </td>
                            <td><a href={"/lecturer/editSurname"}><FiEdit   /></a></td>
                        </tr>
                        <tr>
                            <th>Hasło</th>
                            <td>****</td>
                            <td> <a href={"/lecturer/editPass"}><FiEdit   /></a></td>
                        </tr>

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ProfilLecturer;