import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-contact-form',
    templateUrl: './contact-form.component.html',
    styleUrls: ['./contact-form.component.scss'],
    standalone: true,
    imports: [ReactiveFormsModule, CommonModule]
})
export class ContactFormComponent implements OnInit {
    public contactForm!: FormGroup;
    public successMessage: string | null = null;

    ngOnInit(): void {
        this.contactForm = new FormGroup({
            email: new FormControl('', [Validators.required, Validators.email]),
            message: new FormControl('', [Validators.required, Validators.maxLength(300)])
        });
    }

    onSubmit() {
        if (this.contactForm.valid) {
            console.log('Form Submitted!', this.contactForm.value);
            this.successMessage = 'Demande de contact envoyée avec succès';
            this.contactForm.reset();
        }
    }
}