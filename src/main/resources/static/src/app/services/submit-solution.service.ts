import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { SubmissionRequestModel } from '../shared';

@Injectable({
  providedIn: 'root'
})
export class SubmitSolutionService {

  private api = `${environment.api}`;

  constructor(private http: HttpClient) { }

  public submitSolution(submitRequest: SubmissionRequestModel): Observable<any> {
    return this.http.post(this.api + '/submit', submitRequest);
  }

  public getSubmision(submissionID: number): Observable<any> {
    return this.http.get(this.api + '/submission/' + submissionID);
  }

  public getSubmissionsByProblem(problemID: number): Observable<any> {
    return this.http.get(this.api + '/submissions/problem/' + problemID);
  }

}
