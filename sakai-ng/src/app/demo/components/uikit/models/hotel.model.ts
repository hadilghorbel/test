export interface Hotel {
    name?: string,
    rating?: string,
    stock?: number,
    reservationType?: ReservationType,
    image?: string,
    status?: Status,
    price?: number,
    category?: Category

}
export enum  ReservationType {
    DEJ_INCLUS = "Petit déjeuner inclus",
    RESORT_MARINE_SPA = "Resort & Marine Spa",
    TOUS_COMPRIS = "Tous compris",
    THALASO_SPA = "Thalaso Spa",
    BEACH_RESORT = "Beach Resort"
}
export enum  Status{
    BOOKED = "Booked",
    AVAILABLE = "Available"
}
export enum Category {
    TOURISTIQUE = "Complexe touristique",
    PETIT_HOTEL = "Petit hotel"
}
