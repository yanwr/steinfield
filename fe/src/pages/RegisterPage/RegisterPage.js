import React, { useState } from 'react';
import { connect } from 'react-redux';
import { useHistory, Link } from 'react-router-dom';
import { registerNewUser } from '../../shared/user/User.reducer';
import { Form, Image, Container, Row, Col} from 'react-bootstrap';
import NavBarComponent from '../../components/NavBar/NavBarComponent';
import FooterComponent from '../../components/Footer/FooterComponent';
import CustomButton from '../../components/Buttons/CustomButton';
import Loading from '../../components/Loading/Loading';
import './style.css';

function RegisterPage(props) {
    const name = useFormInput(''); // semi-state
    const email = useFormInput(''); // semi-state
    const phone = useFormInput(''); // semi-state
    const password = useFormInput(''); // semi-state
    const confirmpassword = useFormInput(''); // semi-state
    const navigation = useHistory(); 

    function useFormInput (initialValue) {
        const [value, setValue] = useState(initialValue); // state
        function handleChange(e) {
            setValue(e.target.value);
        };
        return {
            value,
            onChange: handleChange
        };
    };

    function handleDoRegistro(e) {
        e.preventDefault();
        if(email.value === '' || password.value === '' || name.value === '' || confirmpassword.value === '' || phone.value === '') return alert("Preencha todos os campos!");
        else if (password.value == confirmpassword.value){
            props.registerNewUser(name.value, email.value, password.value, phone.value, navigation);
        } else {
            alert("As senhas inseridas devem ser iguais!");
        }
    };

    function renderMainContainer() {
        const { loading } = props;
        return(
            <Container fluid className="login-container">
                <Row>
                    <Col sm={4} className="form-container">
                        <Form onSubmit={handleDoRegistro}>
                            <h1>CADASTRO</h1>
                            <Form.Group controlId="formBasicNome" >
                                <Form.Label>Nome</Form.Label>
                                <Form.Control 
                                    {...name}
                                    type="text" 
                                    placeholder="Insira seu nome" 
                                />
                            </Form.Group>
                            <Form.Group controlId="formBasicEmail" >
                                <Form.Label>Email</Form.Label>
                                <Form.Control 
                                    {...email}
                                    type="email" 
                                    placeholder="Insira seu email" 
                                />
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Senha</Form.Label>
                                <Form.Control 
                                    {...password}
                                    type="password" 
                                    placeholder="Insira sua senha" 
                                />
                            </Form.Group>
                            <Form.Group controlId="formBasicRepeatPassword">
                                <Form.Label>Repita sua senha</Form.Label>
                                <Form.Control 
                                    {...confirmpassword}
                                    type="password" 
                                    placeholder="Insira sua senha" 
                                />
                            </Form.Group>
                            <Form.Group controlId="formBasicFone" >
                                <Form.Label>Telefone</Form.Label>
                                <Form.Control 
                                    {...phone}
                                    type="text" 
                                    placeholder="Insira seu telefone" 
                                />
                            </Form.Group>
                            <Form.Group as={Row}>
                                <button 
                                    type="submit" 
                                    className="btn-login"
                                    disabled={loading}
                                > 
                                { 
                                    !loading ? "Cadastrar" 
                                    :<Loading 
                                        txt={"Loading ..."}
                                        style={{ marginRight: 10 }}
                                    /> 
                                }
                                </button>
                            </Form.Group>
                            <center>
                                <Image src="https://64.media.tumblr.com/beefdedff14135bcfd92ae4b58f67eaa/8dcff200d66f1b68-69/s500x750/886b24d40fce744ae9b835eb6ab968ee90e53b23.png" width="150" height="150" />
                            </center>
                        </Form>
                    </Col>
                    <Col sm={7} className="text-container">
                        <h3>Bem vindo ao portal da Biblioteca Steinfield !</h3>
                        <p>
                            No ramo  desde  o  século X,  agora  nossos  serviços  podem  ser  requisitados  pela  internet! 
                            Alugue  ou  compre  livros  e  busque  em  uma  das  nossas  franquias  ou requisite  um  delivery.
                        </p>
                    </Col>                
                </Row>
            </Container>
        );
    };

    return(
        <>
            <NavBarComponent>
                <CustomButton 
                    to={"/register"}
                    title={"Cadastro"}
                />
                <CustomButton 
                    to={"/login"}
                    title={"Login"}
                />
            </NavBarComponent>
            {renderMainContainer()}
            <FooterComponent />
        </>
    );
}

const mapStateToProps = ({ userState }) => ({
    loading: userState.loading
});

const mapDispatchToProps = {
    registerNewUser
};

export default connect(mapStateToProps, mapDispatchToProps)(RegisterPage);