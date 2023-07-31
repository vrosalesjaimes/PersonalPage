import { Author } from './author.interface';

export interface Reference {
  id: number;
  name: string;
  type: string;
  date: Date;
  title: string;
  source: string;
  location: string;
  edition?: number; 
  authors: Author[];
}
