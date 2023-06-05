import { Link } from "react-router-dom"


export const LandingPage = () => {

    


  return (
    <>
        <h1 className="text-[4rem] font-bold mt-[10rem]">Plan your dream vacation.</h1>
        <div className="mt-4 flex flex-row justify-center [&>*>*]:bg-indigo-600 [&>*>*]:rounded-lg [&>*>*]:w-32 [&>*>*]:h-12 [&>*]:font-bold [&>*]:m-16 [&>*>*]:text-xl [&>*>*]:items-center ">
            <Link to="/login">
                <button className="hover:drop-shadow-[0px_0px_40px_rgba(159,134,217,0.9)] transition duration-150 ease-out hover:shadow-[-16px_16px_0px_rgba(0,0,0,0.25)] hover:translate-x-4 hover:translate-y-[-1rem]   hover:bg-indigo-800   ">
                    Login
                </button>
            </Link>
            <Link to="/register">
                <button className="hover:drop-shadow-[0px_0px_40px_rgba(159,134,217,0.9)] transition duration-150 ease-out hover:shadow-[-16px_16px_0px_rgba(0,0,0,0.25)] hover:translate-x-4 hover:translate-y-[-1rem]   hover:bg-indigo-800">
                    Register
                </button>
            </Link>
        </div>
    </>
  )
}

