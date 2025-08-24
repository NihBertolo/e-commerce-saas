resource "aws_db_subnet_group" "rds_subnet_group" {
  name       = "${var.name}-subnet-group"
  subnet_ids = var.private_subnets
}

resource "aws_db_instance" "postgres" {
  allocated_storage    = 20
  engine               = "postgres"
  engine_version       = "16"
  instance_class       = var.instance_class
  name                 = var.db_name
  username             = var.username
  password             = var.password
  skip_final_snapshot  = true
  publicly_accessible  = false
  db_subnet_group_name = aws_db_subnet_group.rds_subnet_group.name
  vpc_security_group_ids = var.security_groups
  multi_az             = true
}
