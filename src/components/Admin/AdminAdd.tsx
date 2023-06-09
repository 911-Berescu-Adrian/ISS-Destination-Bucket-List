import { Link, useNavigate } from "react-router-dom"
import { BACKEND_URL } from "../../constants";
import axios from "axios";
import { useState } from "react";


export const AdminAdd = () => {

    const navigate = useNavigate();
    

    const [destination, setDestination] = useState<Destination>({
        title: "",
        geolocation: "",
        image: "",
        description: "",
        startDate: new Date(),
        endDate: new Date()
    })

    const handleSubmit = async (e: {preventDefault: () => void}) => {
        e.preventDefault();
        console.log(destination);
        try{
            const response = await axios.post(BACKEND_URL+'/admin/destination', destination)
            navigate('/admin/home');
        }
        catch(error){
            console.log(error);
        }
    }

    return (
        <>
            <Link to={"/admin/home"}>
                <svg className="mt-[10rem] p-2 hover:bg-[rgb(0,0,0,0.2)] rounded-full w-16 ml-[13rem] " xmlns="http://www.w3.org/2000/svg" viewBox="0 0 28 28" width="64" height="64"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M7.828 11H20v2H7.828l5.364 5.364-1.414 1.414L4 12l7.778-7.778 1.414 1.414z"></path></svg>
            </Link> 
            <form onSubmit={handleSubmit} className="mx-[22rem]">
                <div className="grid grid-cols-2 grid-flow-row  gap-6 mt-[1rem] [&>*]:rounded-lg [&>*]:h-[2.25rem] [&>*]:p-2 [&>*]:px-2.5">
                    <label htmlFor="title">Title</label>
                    <input type="text" name="title" id="title" value={destination.title} onChange={(e) => setDestination({...destination, title: e.target.value})} />
                    <label htmlFor="description">Description</label>
                    <input type="text" name="description" id="description"  value={destination.description} onChange={(e) => setDestination({...destination, description: e.target.value})}/>
                    <label htmlFor="image">Image</label>
                    <input type="file" name="image" id="image" className="text-center" value={destination.image} onChange={(e) => setDestination({...destination, image: e.target.value})}/>
                    <label htmlFor="geolocation">Geolocation</label>
                    <input type="text" name="geolocation" id="geolocation" value={destination.geolocation} onChange={(e) => setDestination({...destination, geolocation: e.target.value})}/>
                </div>
                <button type="submit" className="bg-[rgb(49,46,129,0.35)] hover:bg-[rgb(69,66,149,0.45)] mt-8 w-44 h-12 text-indigo-400 rounded-lg font-bold">Add Destination</button>
            </form>
        </>
    )
}