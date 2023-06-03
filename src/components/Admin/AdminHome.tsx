import axios from "axios";

export const AdminHome = () => {
  interface Destination {
    title: string;
    geolocation: string;
    image: string;
    description: string;
  }

  const destinations: Destination[] = [];

  destinations.push({
    title: "Madrid",
    geolocation: "111.222, -33.444",
    image: "newdestination.jpg",
    description: "This is the description for the new destination.",
  });

  destinations.push({
    title: "Cluj-Napoca",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Prague",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Munchen",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Milano",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Roma",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Napoli",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Stockholm",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "London",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Budapest",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Bucharest",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Lisbon",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Torino",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Barcelona",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Valencia",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  destinations.push({
    title: "Frankfurt",
    geolocation: "",
    image: "",
    description: "miauritius",
  });

  async function getGeolocation(
    destinationTitle: string
  ): Promise<string | null> {
    try {
      const apiKey = "56b4236c91254fb58a68e7b269204e7b"; // Replace with your own OpenCage Geocoder API key
      const encodedDestination = encodeURIComponent(destinationTitle);

      const response = await axios.get(
        `https://api.opencagedata.com/geocode/v1/json?q=${encodedDestination}&key=${apiKey}`
      );

      const { results } = response.data;

      if (results.length > 0) {
        const { lat, lng } = results[0].geometry;
        return `${lat}, ${lng}`;
      } else {
        return null; // Geolocation not found for the destination
      }
    } catch (error) {
      console.error("Error retrieving geolocation:", error);
      return null;
    }
  }

  // pixabay api key: 36998343-211d018d1523f85df7020a1f6

  async function getCityImage(cityName: string): Promise<string | null> {
    try {
      const apiKey = "36998343-211d018d1523f85df7020a1f6"; // Replace with your own Pixabay API key
      const encodedCityName = encodeURIComponent(cityName);

      const response = await axios.get(
        `https://pixabay.com/api/?key=${apiKey}&q=${encodedCityName}&category=places`
      );

      const { hits } = response.data;

      if (hits.length > 0) {
        const randomIndex = Math.floor(Math.random() * hits.length);
        const imageUrl = hits[randomIndex].webformatURL;
        return imageUrl;
      } else {
        return null; // No image found for the city
      }
    } catch (error) {
      console.error("Error retrieving city image:", error);
      return null;
    }
  }

  function cutLastWord(str: string): string {
    const words = str.trim().split(" ");
    words.pop(); // Remove the last word from the array
    return words.join(" ");
  }

  async function getCityDescription(cityName: string): Promise<string | null> {
    try {
      const response = await axios.get(
        `http://api.geonames.org/wikipediaSearchJSON?q=${encodeURIComponent(cityName)}&maxRows=1&username=callisto8008`
      );
  
      const { geonames } = response.data;
  
      if (geonames && geonames.length > 0) {
        const description = geonames[0].summary;
        const shortDescription = description.substring(0, 120);
        const actualDescription = cutLastWord(shortDescription) + "...";
        return actualDescription;
      } else {
        return null; 
      }
    } catch (error) {
      console.error("Error retrieving city description:", error);
      return null;
    }
  }
  

  // Accessing destinations
  destinations.forEach((destination) => {
    console.log("Title:", destination.title);
    console.log("Geolocation:", destination.geolocation);
    console.log("Image:", destination.image);
    console.log("Description:", destination.description);
    getGeolocation(destination.title).then((geolocation) => {
      if (geolocation) {
        console.log(`Geolocation for ${destination.title}: ${geolocation}`);
      } else {
        console.log(`Geolocation not found for ${destination.title}`);
      }
    });
    getCityImage(destination.title).then((imageUrl) => {
      if (imageUrl) {
        console.log(`Image URL for ${destination.title}: ${imageUrl}`);
      } else {
        console.log(`No image found for ${destination.title}`);
      }
    });
    getCityDescription(destination.title).then((description) => {
      if (description) {
        console.log(`Description for ${destination.title}: ${description}`);
      } else {
        console.log(`Description not found for ${destination.title}`);
      }
    });
  });

  return (
    <>
      <p>admin</p>
    </>
  );
};
