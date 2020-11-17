import { Injectable } from '@angular/core';
import {API_BASE_URL} from "../../constants/constants";
import {HttpClient, HttpRequest} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable()
export class FileService {

  constructor(private http: HttpClient) { }

  deleteFile(id){
    return this.http.delete(API_BASE_URL + `/files/delete/${id}`);
  }

  uploadFile(fileToUpload: File, id: string, name: string, description: string) {
    const file: FormData = new FormData();
    file.append('file', fileToUpload);
    file.append('name', name);
    file.append('description', description);
    const req = new HttpRequest('POST', API_BASE_URL + `/files/uploadFile/${id}`, file);

    return this.http.request(req);
  }

  public download(name): Observable<any> {
    return this.http.get(API_BASE_URL + `/files/downloadFile/${name}`, { responseType: 'blob' }).pipe(
      map((res: Blob) => {
        return new Blob([res.slice()]);
      })
    );
  }
}
