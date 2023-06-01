import './App.css'
import { Login } from './components/Account/Login'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import { LandingPage } from './components/LandingPage'
import { Register } from './components/Account/Register'


function App() {


  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage/>} /> 
          <Route path='/login' element={<Login/>} />
          <Route path='/register' element={<Register/>} />
        </Routes>
      </Router>
    </>
  )
}

export default App
