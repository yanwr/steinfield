import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { NavDropdown } from 'react-bootstrap';
import { doLogout } from '../../shared/user/User.reducer';
import { getUserFromLocalStorage } from '../../shared/user/UserService';
import { loadProducts, handleFilterProduct } from './HomePage.reducer';
import NavBarComponent from '../../components/NavBar/NavBarComponent';
import FooterComponent from '../../components/Footer/FooterComponent';
import ListProducts from '../../components/ListProducts/ListProducts'
import SearchList from '../../components/SearchList/SearchList';
import CustomButton from '../../components/Buttons/CustomButton';
import Loading from '../../components/Loading/Loading';
import './style.css';

function HomePage(props) {
    const navigation = useHistory();
    const { name, admin, id } = getUserFromLocalStorage();
    const { products = [], loadingUser, loadingProduct } = props;

    useEffect(() => {
        props.loadProducts();
    }, []);
    

    function renderProducts() {
        return <>
                <SearchList onSearch={(v) => props.handleFilterProduct(v, products)}/>
                <ListProducts data={products}/>
            </>
    };

    if (loadingUser) {
        return <Loading />
    }
    return (
        <>
            <NavBarComponent>
               { name ? <>
                        <NavDropdown title={name} id="basic-nav-dropdown">
                            <CustomButton
                                toDo={() => props.doLogout(navigation, id)}
                                title={"Logout"}
                            />
                        </NavDropdown>
                    </>
                    : <>
                        <CustomButton 
                            to={"/register"}
                            title={"Cadastro"}
                        />
                        <CustomButton 
                            to={"/login"}
                            title={"Login"}
                        />
                    </> 
                }
            </NavBarComponent>
            <div className="banner"><img src={require('../../img/banner.png')} alt="" width="100%" /></div>
            { !loadingProduct ? renderProducts() : <Loading /> }
            <FooterComponent />
        </>
    );
}

const mapStateToProps = ({ userState, homeState }) => ({
    loadingUser: userState.loading,
    loadingProduct: homeState.loading,
    products: homeState.products
});

const mapDispatchToProps = {
    doLogout,
    loadProducts,
    handleFilterProduct
};

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);