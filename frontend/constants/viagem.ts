import { Destino } from "./Destino"

export class Viagem {
    public id: number = 0
    public nome: string = ''
    public dataSaida: Date = new Date()
    public dataChegada: Date = new Date()
    public valor: number = 0
    public destinos: Destino[] = []
}
