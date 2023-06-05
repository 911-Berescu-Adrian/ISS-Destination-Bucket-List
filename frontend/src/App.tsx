import './App.css'
import { Login } from './components/Account/Login'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import { LandingPage } from './components/LandingPage'
import { Register } from './components/Account/Register'
import { AdminHome } from './components/Admin/AdminHome'
import { UserHome } from './components/User/UserHome'
import { AdminAdd } from './components/Admin/AdminAdd'
import { AdminUpdate } from './components/Admin/AdminUpdate'
import { UserAdd } from './components/User/UserAdd'
import { UserUpdate } from './components/User/UserUpdate'


function App() {


  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage/>} /> 
          <Route path='/login' element={<Login/>} />
          <Route path='/register' element={<Register/>} />
          <Route path='/admin/home' element={<AdminHome/>} ></Route>
          <Route path='/admin/add' element={<AdminAdd/>}></Route>
          <Route path='/admin/update' element={<AdminUpdate/>}></Route>
          <Route path='/home' element={<UserHome/>}></Route>
          <Route path='/add' element={<UserAdd/>}></Route>
          <Route path='/update' element={<UserUpdate/>}></Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
