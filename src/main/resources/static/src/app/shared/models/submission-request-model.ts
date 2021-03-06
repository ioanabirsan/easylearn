import { SourceModel } from './source-model';

export class SubmissionRequestModel {

    public language: string;
    public mainSource: string;
    public problemId: number;
    public sources: Array<SourceModel>;
    public userId: number;

    constructor() {
        this.language = null;
        this.mainSource = null;
        this.problemId = null;
        this.sources = null;
        this.userId = null;
    }
}
