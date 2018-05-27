import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/public/login/login.component';
import { ForgotComponent } from './components/public/forgot/forgot.component';
import { RegisterComponent } from './components/public/register/register.component';
import { SignComponent } from './components/public/sign/sign.component';
import { TopComponent } from './components/pages/top/top.component';
import { Error404Component } from './components/pages/error404/error404.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';
import { ProblemComponent } from './components/pages/problem/problem.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { UserDashboardComponent } from './components/pages/user-dashboard/user-dashboard.component';
import { ProblemsCategoryComponent } from './components/pages/problems-category/problems-category.component';
import { AddProblemComponent } from './components/pages/add-problem/add-problem.component';
import { NotallowedComponent } from './components/pages/notallowed/notallowed.component';
import { SubmissionsComponent } from './components/pages/submissions/submissions.component';
import { LogoutComponent } from './components/pages/logout/logout.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: SignComponent,
    children: [{ path: '', component: LoginComponent }] },
  { path: 'forgot', component: SignComponent,
    children: [{ path: '', component: ForgotComponent }] },
  { path: 'register', component: SignComponent,
    children: [{ path: '', component: RegisterComponent }] },
  { path: 'top', component: TopComponent},
  { path: 'about-us', component: AboutUsComponent },
  { path: 'problem', component: ProblemComponent },
  { path: 'problems-category/:id', component: ProblemsCategoryComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'add-problem', component: AddProblemComponent },
  { path: '404', component: Error404Component },
  { path: 'notallowed', component: NotallowedComponent},
  { path: 'submissions', component: SubmissionsComponent},
<<<<<<< HEAD
  { path: 'logout', component: LogoutComponent},
=======
  { path: 'tests', component: TestsComponent},
  { path: 'runs', component: RunsComponent},
>>>>>>> origin/master
  { path: '**', redirectTo: '/404' }

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
