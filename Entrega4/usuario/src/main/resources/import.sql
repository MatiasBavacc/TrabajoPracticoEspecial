INSERT INTO usuario (usuario, password, nombre, apellido, email, celular, rol, habilitado) VALUES ('mati', '$2a$10$bXSJQrIviI/aW.ZShkcofen06fWmeOawPBe20Kt1SaCBdyUOz6zNC', 'Matías', 'Bava', 'mati@example.com', '11111111', 'ADMIN', true);
INSERT INTO usuario (usuario, password, nombre, apellido, email, celular, rol, habilitado) VALUES ('ale', '$2a$10$bXSJQrIviI/aW.ZShkcofen06fWmeOawPBe20Kt1SaCBdyUOz6zNC', 'Alejandro', 'Garay', 'alecito@example.com', '22222222', 'ADMIN', true);
INSERT INTO usuario (usuario, password, nombre, apellido, email, celular, rol, habilitado) VALUES ('marcelo', '$2a$10$bXSJQrIviI/aW.ZShkcofen06fWmeOawPBe20Kt1SaCBdyUOz6zNC', 'Marcelo', 'Lopez', 'marcelo@example.com', '33333333', 'USUARIO', true);
INSERT INTO usuario (usuario, password, nombre, apellido, email, celular, rol, habilitado) VALUES ('juana', '$2a$10$ecvHW.VZoVQELZt.p2i4QOfNTsAJHZgVUAmIrKfaqSD0RjTAXt3oy', 'Juana', 'Lopez', 'juana@example.com', '44444444', 'USUARIO', true);

INSERT INTO cuenta (monto, tipo_cuenta, km_recorridos, fecua_limite_km) VALUES (1500.50, 1, 0, '2025-12-31');
INSERT INTO cuenta (monto, tipo_cuenta, km_recorridos, fecua_limite_km) VALUES (3200.00, 2, 100, '2026-01-15');
INSERT INTO cuenta (monto, tipo_cuenta, km_recorridos, fecua_limite_km) VALUES (980.75, 1, 0, '2025-10-10');
INSERT INTO cuenta (monto, tipo_cuenta, km_recorridos, fecua_limite_km) VALUES (50000.00, 2, 80.9, '2027-05-20');
INSERT INTO cuenta (monto, tipo_cuenta, km_recorridos, fecua_limite_km) VALUES (250.00, 1, 0, '2025-03-03');